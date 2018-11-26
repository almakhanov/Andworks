package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "MasterSessionIds")
data class MasterSessionIds(
        @SerializedName("masterId") val masterId: Int,
        @SerializedName("sessionId") val sessionId: Int): Serializable