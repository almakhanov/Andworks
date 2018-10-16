package kz.batana.khanproject.audio_list

import android.annotation.SuppressLint
import kz.batana.khanproject.entity.AudioObject
import kz.batana.khanproject.Logger
import kz.darlogistics.courier.core.util.BasePresenter


class AudioListPresenter(private val repository: AudioListContract.Repository) :
        BasePresenter<AudioListContract.View>(), AudioListContract.Presenter {

    @SuppressLint("CheckResult")
    override fun getAudioList() {
        Logger.msg("accepted", "MainPresenter")
        repository.getAudioList()?.subscribe{
            Logger.msg("accepted", "subscribe")
            getView()?.setAudioList(it as ArrayList<AudioObject>)
        }
    }

    override fun viewIsReady() {}

    override fun destroy() {}

}