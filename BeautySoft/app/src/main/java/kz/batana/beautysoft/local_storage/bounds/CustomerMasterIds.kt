package kz.batana.beautysoft.local_storage.bounds

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "CustomerMasterIds")
data class CustomerMasterIds (
        @SerializedName("customerId") val customerId: Int,
        @SerializedName("masterId") val masterId: Int): Serializable