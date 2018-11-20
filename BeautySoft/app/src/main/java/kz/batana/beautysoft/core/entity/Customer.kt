package kz.batana.beautysoft.core.entity

import java.io.Serializable

data class Customer(
        var id: Int?,
        var firstName: String?,
        var lastName: String?,
        var age: Int?,
        var username: String?,
        var email: String?,
        var telNumber: String?,
        var salonIds: List<Int>?,
        var masterIds: List<Int>?,
        var hairColor: String?,
        var photoIds: List<Int>?,
        var profilePhotoId: Int?,
        var productIds: List<Int>?,
        var sessionIds: List<Int>?
) : Serializable{
    constructor() : this(null,null, null, null, null, null, null,
            null, null, null, null, null, null, null)
}