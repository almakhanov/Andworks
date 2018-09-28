package kz.batana.khanproject

import kz.darlogistics.courier.core.util.IPresenter
import kz.darlogistics.courier.core.util.IView

interface MainContract{
    interface View : IView<Presenter> {
        fun setAudioList(list: List<AudioObject>?)
    }

    interface Presenter : IPresenter<View> {
        fun saveAudio(soundBytes: ByteArray?)
    }

    interface Repository {
        fun saveAudio(ao: AudioObject)
    }
}