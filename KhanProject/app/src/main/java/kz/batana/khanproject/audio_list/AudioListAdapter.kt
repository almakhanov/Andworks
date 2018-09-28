package kz.batana.khanproject.audio_list

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kz.batana.khanproject.AudioObject
import kz.batana.khanproject.R

class AudioListAdapter(private var dataset: ArrayList<AudioObject>,
                       private val listener: AudioListListenter) : RecyclerView.Adapter<AudioListAdapter.AudioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view_audio_list, parent, false)
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

            fabPlay.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val pos : Int = adapterPosition
            val audio : AudioObject = dataset[pos]
            Log.d("accepted", "OnClicked $audio")
            listener.onAudioClicked(audio)
        }

    }
    interface AudioListListenter{
        fun onAudioClicked(audioObject: AudioObject)
    }

}

