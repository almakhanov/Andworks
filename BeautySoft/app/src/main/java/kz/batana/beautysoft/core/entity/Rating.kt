package kz.batana.beautysoft.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Rating")
data class Rating(
        @PrimaryKey
        @SerializedName("id")
        var id: Int?,
        @SerializedName("value") var value: Int,
        @SerializedName("customerId") val customerId: Int?,
        @SerializedName("masterId") val masterId: Int?
) : Serializable