package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "CustomerSessionIds")
data class CustomerSessionIds(
        @SerializedName("customerId") val customerId: Int,
        @SerializedName("sessionId") val sessionId: Int): Serializable
