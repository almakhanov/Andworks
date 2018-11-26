package kz.batana.beautysoft.local_storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.beautysoft.core.entity.Product

@Dao
interface ProductDao{
    @Insert
    fun insert(item: Product)

    @Query("SELECT * FROM Product")
    fun get(): Flowable<List<Product>>

    @Query("DELETE FROM Product")
    fun delete()
}