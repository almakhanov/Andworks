package kz.batana.beautysoft.local_storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.beautysoft.core.entity.Rating

@Dao
interface RatingDao{
    @Insert
    fun insert(item: Rating)

    @Query("SELECT * FROM Rating")
    fun get(): Flowable<List<Rating>>

    @Query("DELETE FROM Rating")
    fun delete()
}