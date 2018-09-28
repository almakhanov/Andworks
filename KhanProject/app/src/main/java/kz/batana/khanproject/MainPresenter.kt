package kz.batana.khanproject

import android.annotation.SuppressLint
import kz.darlogistics.courier.core.util.BasePresenter


class MainPresenter(private val repository: MainContract.Repository) :
        BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun saveAudio(soundBytes: ByteArray?) {
        repository.saveAudio(AudioObject(0,"Audio_name", soundBytes!!))
    }

    override fun viewIsReady() {}

    override fun destroy() {}

}