package kz.batana.khanproject

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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