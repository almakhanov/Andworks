package kz.batana.beautysoft.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Customer")
data class Customer(
        @PrimaryKey
        @SerializedName("id")
        var id: Int?,
        @SerializedName("firstName") var firstName: String?,
        @SerializedName("lastName") var lastName: String?,
        @SerializedName("age") var age: Int?,
        @SerializedName("username") var username: String?,
        @SerializedName("email") var email: String?,
        @SerializedName("password") var password: String?,
        @SerializedName("telNumber") var telNumber: String?,
//        @SerializedName("salonIds") var salonIds: List<Int>?,+
//        @SerializedName("masterIds") var masterIds: List<Int>?,+
        @SerializedName("hairColor") var hairColor: String?,
//        @SerializedName("photoIds") var photoIds: List<Int>?,+
        @SerializedName("profilePhotoId") var profilePhotoId: Int?
//        @SerializedName("productIds") var productIds: List<Int>?,+
//        @SerializedName("sessionIds") var sessionIds: List<Int>?+
) : Serializable