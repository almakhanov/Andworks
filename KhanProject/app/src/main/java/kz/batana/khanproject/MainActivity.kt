package kz.batana.khanproject

import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kz.batana.khanproject.audio_list.AudioListActivity
import org.koin.android.ext.android.inject
import java.io.*


class MainActivity : AppCompatActivity(), MainContract.View {

    override val presenter: MainContract.Presenter by inject()

    var time: Long = 3
    private var mRecorder: MediaRecorder? = null
    private var mPlayer: MediaPlayer? = null
    private var mFileName: String = ""
    private val LOG_TAG = "AudioRecordTest"
    private var soundBytes: ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
        text_view_timer.text = time.toString()
        progressCountDown.max = (time*1000).toInt()
        // Record to the external cache directory for visibility
        mFileName = "${externalCacheDir.absolutePath}/audiorecordtest.3gp"

        fab_play.setOnClickListener{
            timer(time*1000, 10).start()
            fab_play.isEnabled = false
            startRecording()
        }

        fab_list.setOnClickListener{
            startActivity(Intent(this, AudioListActivity::class.java))
        }
    }


    private fun timer(millisInFuture:Long,countDownInterval:Long) : CountDownTimer{
        return object: CountDownTimer(millisInFuture, countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                val leftTime = millisUntilFinished.toInt()

                progressCountDown.progress = (time *1000 - millisUntilFinished).toInt()

                text_view_timer.text = ((leftTime+1000) / 1000).toString()
            }

            override fun onFinish() {
                text_view_timer.text = "0"
                progressCountDown.progress = (time *1000).toInt()
                fab_play.isEnabled = true
                stopRecording()
                startPlaying()
            }
        }
    }


    private fun startRecording() {
        mRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(mFileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }

    private fun stopRecording() {
        mRecorder?.apply {
            stop()
            release()
        }
        mRecorder = null

        try {
            val inputStream = contentResolver.openInputStream(Uri.fromFile(File(mFileName)))
            soundBytes = ByteArray(inputStream!!.available())
            soundBytes = toByteArray(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        presenter.saveAudio(soundBytes)

        Log.d("accepted", soundBytes.toString())

    }

    @Throws(IOException::class)
    fun toByteArray(`in`: InputStream): ByteArray {
        val out = ByteArrayOutputStream()
        var read = 0
        val buffer = ByteArray(1024)
        while (read != -1) {
            read = `in`.read(buffer)
            if (read != -1)
                out.write(buffer, 0, read)
        }
        out.close()
        return out.toByteArray()
    }



    ///////////////////////////////



    private fun startPlaying() {


        val fos: FileOutputStream
        try {
            fos = FileOutputStream(File(mFileName))
            fos.write(soundBytes)
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mPlayer = MediaPlayer().apply {
            try {
                setDataSource(mFileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    override fun setAudioList(list: List<AudioObject>?) {
        Logger.msg("accepted", "setAudioList")
        if (list != null) {
            Logger.msg("accepted", "${list.size}")
        }else{
            Logger.msg("accepted", "NuLL")
        }
    }

    private fun stopPlaying() {
        mPlayer?.release()
        mPlayer = null
    }


}
