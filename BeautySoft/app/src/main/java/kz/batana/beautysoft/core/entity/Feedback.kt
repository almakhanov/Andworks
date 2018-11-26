package kz.batana.beautysoft.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Feedback")
data class Feedback(
        @PrimaryKey
        @SerializedName("id")
        var id: Int?,
        @SerializedName("text") val text: String,
        @SerializedName("customerId") val customerId: Int,
        @SerializedName("date") val date: String
) : Serializable