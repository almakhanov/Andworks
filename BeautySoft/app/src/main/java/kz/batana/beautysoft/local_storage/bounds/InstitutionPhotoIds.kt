package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "InstitutionPhotoIds")
data class InstitutionPhotoIds (
        @SerializedName("institutionId") val institutionId: Int,
        @SerializedName("photoId") val photoId: Int): Serializable