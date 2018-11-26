package kz.batana.beautysoft.local_storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.beautysoft.core.entity.Feedback

@Dao
interface FeedbackDao{
    @Insert
    fun insert(item: Feedback)

    @Query("SELECT * FROM Feedback")
    fun get(): Flowable<List<Feedback>>

    @Query("DELETE FROM Feedback")
    fun delete()
}