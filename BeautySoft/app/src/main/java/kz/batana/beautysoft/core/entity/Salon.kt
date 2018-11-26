package kz.batana.beautysoft.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Salon")
data class Salon (
        @PrimaryKey
        @SerializedName("id")
        var id: Int?,
        @SerializedName("title") var title : String,
        @SerializedName("date") var date : String,
        @SerializedName("content") var content : String,
        @SerializedName("imageUrl") var imageUrl : String
): Serializable