package kz.batana.khanproject.audio_list

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.khanproject.Logger
import kz.batana.khanproject.R
import kz.batana.khanproject.entity.AudioObject
import java.util.concurrent.TimeUnit

class AudioListAdapter(private var dataset: ArrayList<AudioObject>,
                       private val context: Context,
                       private val listener: AudioListAdapter.AudioListListenter) : RecyclerView.Adapter<AudioListAdapter.AudioViewHolder>() {

    private var isPlaying = false
    private var lastIndex = -1
    private var curIndex = -1
    private var isPaused = false
    private var isStoped = true
    private val pref = AudioPrefUtil(context)
    private var isSelected = ArrayList<Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view_audio_list, parent, false)
        for(i in dataset) isSelected.add(false)
        return AudioViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        holder.bind(dataset[position])
    }


    inner class AudioViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind(item : AudioObject){
            val title = itemView.findViewById<TextView>(R.id.audio_list_title)
            title.text = item.name

            val fabPlay = itemView.findViewById<FloatingActionButton>(R.id.audio_list_play_fab)
            if(isSelected[adapterPosition]){
                fabPlay.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_pause))
            }else{
                fabPlay.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_play_arrow))
            }
            fabPlay.setOnClickListener(this)

            val seekBar = itemView.findViewById<SeekBar>(R.id.audio_list_seek)
//            seekBar.progress =

        }

        override fun onClick(p0: View?) {
            curIndex = adapterPosition
            val audio : AudioObject = dataset[curIndex]

            if(pref.getPosition() == -1){
                listener.onAudioClicked(audio, true)
                isSelected[curIndex] = true
            }else if(pref.getPosition() == curIndex && !isSelected[curIndex]){
                listener.onAudioClicked(audio, true)
                isSelected[curIndex] = true
            }else if(pref.getPosition() == curIndex && isSelected[curIndex]){
                listener.onAudioClicked(audio, false)
                isSelected[curIndex] = false
            }
            else{
                listener.onAudioClicked(audio, true)
                isSelected[curIndex] = true

                if(isSelected[pref.getPosition()!!]){
                    isSelected[pref.getPosition()!!] = false
                    notifyItemChanged(pref.getPosition()!!)
                }

            }
            notifyItemChanged(curIndex)
            pref.saveInstance(curIndex)

        }

    }
    interface AudioListListenter{
        fun onAudioClicked(audioObject: AudioObject, play: Boolean)
    }

    private val timerTime: Long = 3000
    private fun backgroundTimerRun(){
        Observable.interval(10, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext{
                    Logger.msg("accepted", "doOnNext -> $it")
                }
                .takeUntil{
                    it == timerTime
                }
                .doOnComplete{
                    Logger.msg("accepted", "doOnComplete")
                }
                .subscribe()
    }

}

