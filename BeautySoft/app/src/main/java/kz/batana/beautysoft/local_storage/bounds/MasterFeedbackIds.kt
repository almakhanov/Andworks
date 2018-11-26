package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "MasterFeedbackIds")
data class MasterFeedbackIds(
        @SerializedName("masterId") val masterId: Int,
        @SerializedName("feedbackId") val feedbackId: Int): Serializable