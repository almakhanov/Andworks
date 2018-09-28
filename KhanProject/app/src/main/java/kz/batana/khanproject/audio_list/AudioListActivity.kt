package kz.batana.khanproject.audio_list

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_audio_list.*
import kz.batana.khanproject.AudioObject
import kz.batana.khanproject.Logger
import kz.batana.khanproject.R
import org.koin.android.ext.android.inject

class AudioListActivity : AppCompatActivity(), AudioListContract.View, AudioListAdapter.AudioListListenter {

    override val presenter: AudioListContract.Presenter by inject()

    private lateinit var audioListAdapter: AudioListAdapter

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

        presenter.getAudioList()
    }

    override fun setAudioList(list: ArrayList<AudioObject>) {
        recycler_view_waybill_products?.layoutManager = LinearLayoutManager(this)
        audioListAdapter = AudioListAdapter(list, this)
        recycler_view_waybill_products?.adapter = audioListAdapter
    }

    override fun onAudioClicked(audioObject: AudioObject) {
        Logger.msg("accepted", "onAudioClicked Implementation $audioObject")
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
