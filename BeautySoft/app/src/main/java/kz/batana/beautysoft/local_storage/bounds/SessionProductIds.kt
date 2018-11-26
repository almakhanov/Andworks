package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "SessionProductIds")
data class SessionProductIds(
        @SerializedName("sessionId") val sessionId: Int,
        @SerializedName("productId") val productId: Int): Serializable