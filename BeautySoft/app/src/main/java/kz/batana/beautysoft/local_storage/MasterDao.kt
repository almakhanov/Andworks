package kz.batana.beautysoft.local_storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.beautysoft.core.entity.Master

@Dao
interface MasterDao{
    @Insert
    fun insert(item: Master)

    @Query("SELECT * FROM Master")
    fun get(): Flowable<List<Master>>

    @Query("DELETE FROM Master")
    fun delete()
}