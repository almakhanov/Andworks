package kz.batana.beautysoft.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Photo")
data class Photo(
        @PrimaryKey
        @SerializedName("id")
        var id: Int?,
        @SerializedName("url") val url: String) : Serializable