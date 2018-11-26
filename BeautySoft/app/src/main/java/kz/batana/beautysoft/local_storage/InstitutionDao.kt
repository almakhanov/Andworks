package kz.batana.beautysoft.local_storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.beautysoft.core.entity.Institution

@Dao
interface InstitutionDao{
    @Insert
    fun insert(item: Institution)

    @Query("SELECT * FROM Institution")
    fun get(): Flowable<List<Institution>>

    @Query("DELETE FROM Institution")
    fun delete()
}