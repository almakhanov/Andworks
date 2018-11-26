package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "InstitutionMasterIds")
data class InstitutionMasterIds(
        @SerializedName("institutionId") val institutionId: Int,
        @SerializedName("masterId") val masterId: Int): Serializable