package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "CustomerPhotoId")
data class CustomerPhotoId (
        @SerializedName("customerId") val customerId: Int,
        @SerializedName("photoId") val photoId: Int): Serializable