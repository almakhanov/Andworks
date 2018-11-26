package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "InstitutionProductIds")
data class InstitutionProductIds (
        @SerializedName("institutionId") val institutionId: Int,
        @SerializedName("productId") val productId: Int): Serializable