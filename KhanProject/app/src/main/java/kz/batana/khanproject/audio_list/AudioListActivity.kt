package kz.batana.khanproject.audio_list

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_audio_list.*
import kz.batana.khanproject.R
import kz.batana.khanproject.entity.AudioObject
import org.koin.android.ext.android.inject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class AudioListActivity : AppCompatActivity(), AudioListContract.View, AudioListAdapter.AudioListListenter {

    override val presenter: AudioListContract.Presenter by inject()

    private lateinit var audioListAdapter: AudioListAdapter
    private var mPlayer: MediaPlayer? = null
    private var mFileName: String = ""
    private val LOG_TAG = "AudioRecordTest"
    private var soundBytes: ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_list)
        presenter.attachView(this)


        //Toolbar
        setSupportActionBar(toolbar_waybill_products)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
            this.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            this.setTitle(R.string.list_title)
        }

        mFileName = "${externalCacheDir.absolutePath}/audiorecordtest.3gp"

        presenter.getAudioList()
    }

    override fun setAudioList(list: ArrayList<AudioObject>) {
        recycler_view_waybill_products?.layoutManager = LinearLayoutManager(this)
        audioListAdapter = AudioListAdapter(list, this, this)
        recycler_view_waybill_products?.adapter = audioListAdapter
    }

    override fun onAudioClicked(audioObject: AudioObject, play: Boolean) {

        if(play){
            soundBytes = audioObject.voice
            startPlaying()
        }else{
            stopPlaying()
        }
    }

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



    private fun stopPlaying() {
        mPlayer?.release()
        mPlayer = null
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }





}
