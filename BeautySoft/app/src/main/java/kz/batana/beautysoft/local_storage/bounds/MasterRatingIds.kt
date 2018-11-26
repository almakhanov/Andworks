package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "MasterRatingIds")
data class MasterRatingIds(
        @SerializedName("masterId") val masterId: Int,
        @SerializedName("ratingId") val ratingId: Int): Serializable