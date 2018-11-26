package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "CustomerInstitutionIds")
data class CustomerInstitutionIds (
        @SerializedName("customerId") val customerId: Int,
        @SerializedName("institutionId") val institutionId: Int): Serializable