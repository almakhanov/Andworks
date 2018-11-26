package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "InstitutionSessionIds")
data class InstitutionSessionIds(
        @SerializedName("institutionId") val institutionId: Int,
        @SerializedName("sessionId") val sessionId: Int): Serializable