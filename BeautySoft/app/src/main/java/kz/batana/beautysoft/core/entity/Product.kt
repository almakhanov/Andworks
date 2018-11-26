package kz.batana.beautysoft.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Product")
data class Product(
        @PrimaryKey
        @SerializedName("id")
        var id: Int?,
        @SerializedName("title") var title: String?,
        @SerializedName("color") var color: String?,
        @SerializedName("amount") var amount: Int?,
        @SerializedName("description") var description: String?,
        @SerializedName("model") var model: String
) : Serializable