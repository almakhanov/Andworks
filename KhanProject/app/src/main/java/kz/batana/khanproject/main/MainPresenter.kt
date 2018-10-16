package kz.batana.khanproject.main

import kz.batana.khanproject.entity.AudioObject
import kz.darlogistics.courier.core.util.BasePresenter


class MainPresenter(private val repository: MainContract.Repository) :
        BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun saveAudio(soundBytes: ByteArray?, title: String) {
        repository.saveAudio(AudioObject(0, title, soundBytes!!))
    }

    override fun viewIsReady() {}

    override fun destroy() {}

}