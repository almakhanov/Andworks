package kz.batana.khanproject.main

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.khanproject.Logger
import kz.batana.khanproject.entity.AudioDao
import kz.batana.khanproject.entity.AudioObject
import org.koin.standalone.KoinComponent

class MainRepository(private val dao: AudioDao) : MainContract.Repository, KoinComponent {

    override fun saveAudio(ao: AudioObject) {
        Logger.msg("accepted", "saved")
        Single.fromCallable {
            dao.insertAudio(ao)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

}