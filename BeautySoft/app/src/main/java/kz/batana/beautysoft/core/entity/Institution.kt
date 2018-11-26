package kz.batana.beautysoft.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Institution")
data class Institution(
        @PrimaryKey
        @SerializedName("id")
        var id: Int?,
        @SerializedName("title") var title: String?,
        @SerializedName("username") var username: String?,
        @SerializedName("telNumber") var telNumber: String?,
        @SerializedName("email") var email: String?,
        @SerializedName("workingYear") var workingYear: Int?,
        @SerializedName("type") var type: String?,
        @SerializedName("rating") var rating: Double?,
//        @SerializedName("customerIds") var customerIds: List<Int>?,+
//        @SerializedName("productIds") var productIds: List<Int>?,+
//        @SerializedName("photoIds") var photoIds: List<Int>?,+
//        @SerializedName("sessionIds") var sessionIds: List<Int>?,+
//        @SerializedName("masterIds") var masterIds: List<Int>?,+
        @SerializedName("isDelivered") var isDelivered: Boolean?,
//        @SerializedName("feedbackIds") var feedbackIds: List<Int>?,+
        @SerializedName("deliveredMasterId") var deliveredMasterId: Int?
): Serializable