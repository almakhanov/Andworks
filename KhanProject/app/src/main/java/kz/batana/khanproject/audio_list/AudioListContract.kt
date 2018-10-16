package kz.batana.khanproject.audio_list

import io.reactivex.Flowable
import kz.batana.khanproject.entity.AudioObject
import kz.darlogistics.courier.core.util.IPresenter
import kz.darlogistics.courier.core.util.IView


interface AudioListContract{
    interface View : IView<Presenter> {
        fun setAudioList(list: ArrayList<AudioObject>)
    }

    interface Presenter : IPresenter<View> {
        fun getAudioList()
    }

    interface Repository {
        fun getAudioList(): Flowable<List<AudioObject>>?
    }
}