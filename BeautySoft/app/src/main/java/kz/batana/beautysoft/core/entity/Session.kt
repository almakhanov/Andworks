package kz.batana.beautysoft.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Session")
data class Session(
        @PrimaryKey
        @SerializedName("id")
        var id: Int?,
        @SerializedName("institutionId") var institutionId: Int?,
        @SerializedName("masterId") var masterId: Int?,
        @SerializedName("customerId") var customerId: Int?,
        @SerializedName("date") var date: String?,
        @SerializedName("price") var price: Int?
//        @SerializedName("productIds") var productIds: List<String>?,+
//        @SerializedName("photoIds") var photoIds: List<Int>?+
) : Serializable