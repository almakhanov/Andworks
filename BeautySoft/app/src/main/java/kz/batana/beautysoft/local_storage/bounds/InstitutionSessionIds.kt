package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "InstitutionSessionIds")
data class InstitutionSessionIds(
        @PrimaryKey
        @SerializedName("institutionId") val institutionId: Int,
        @SerializedName("sessionId") val sessionId: Int): Serializable