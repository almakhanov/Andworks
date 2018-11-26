package kz.batana.beautysoft.local_storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.beautysoft.core.entity.Photo

@Dao
interface PhotoDao{
    @Insert
    fun insert(item: Photo)

    @Query("SELECT * FROM Photo")
    fun get(): Flowable<List<Photo>>

    @Query("DELETE FROM Photo")
    fun delete()
}