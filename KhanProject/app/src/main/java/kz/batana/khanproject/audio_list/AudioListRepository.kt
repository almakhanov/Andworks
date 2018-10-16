package kz.batana.khanproject.audio_list

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.khanproject.entity.AudioDao
import kz.batana.khanproject.entity.AudioObject
import org.koin.standalone.KoinComponent

class AudioListRepository(private val dao: AudioDao) : AudioListContract.Repository, KoinComponent {

    override fun getAudioList() : Flowable<List<AudioObject>>? {
        return dao.getAllAudios()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}