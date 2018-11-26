package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "CustomerProductIds")
data class CustomerProductIds (
        @PrimaryKey
        @SerializedName("customerId") val customerId: Int,
        @SerializedName("productId") val productId: Int): Serializable