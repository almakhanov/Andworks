package kz.batana.beautysoft.core.entity

import java.io.Serializable

data class Master(
        var id: Int?,
        var firstName: String?,
        var lastName: String?,
        var age: Int?,
        var username: String?,
        var email: String?,
        var telNumber: String?,
        var salonIds: List<Int>?,
        var currentSalonId: Int?,
        var photoIds: List<Int>?,
        var profilePhotoId: Int?,
        var productIds: List<Int>?,
        var sessionIds: List<Int>?,
        var rating: Double?,
        var ratingIds: List<Int>?,
        var feedbackIds: List<Int>?,
        var experienceYears: Int?,
        var profession: String?
): Serializable{
    constructor() : this(null,null,null,null,null,null,null,
            null,null, null, null, null, null, null,
            null, null, null, null)
}