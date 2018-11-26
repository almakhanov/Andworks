package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "MasterRatingIds")
data class MasterRatingIds(
        @PrimaryKey
        @SerializedName("masterId") val masterId: Int,
        @SerializedName("ratingId") val ratingId: Int): Serializable