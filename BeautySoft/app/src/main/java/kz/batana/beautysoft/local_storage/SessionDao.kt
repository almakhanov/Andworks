package kz.batana.beautysoft.local_storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.beautysoft.core.entity.Session


@Dao
interface SessionDao{
    @Insert
    fun insert(item: Session)

    @Query("SELECT * FROM Session")
    fun get(): Flowable<List<Session>>

    @Query("DELETE FROM Session")
    fun delete()
}