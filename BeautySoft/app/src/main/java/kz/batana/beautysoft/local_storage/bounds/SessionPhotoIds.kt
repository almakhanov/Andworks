package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "SessionPhotoIds")
data class SessionPhotoIds(
        @SerializedName("sessionId") val sessionId: Int,
        @SerializedName("photoId") val photoId: Int): Serializable