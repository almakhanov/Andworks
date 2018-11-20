package kz.batana.beautysoft.core.entity

import java.io.Serializable

data class Institution(
        var id: Int?,
        var title: String?,
        var username: String?,
        var telNumbers: List<String>?,
        var email: String?,
        var workingYear: Int?,
        var type: List<String>?,
        var productIds: List<Int>?,
        var photoIds: List<Int>?,
        var sessionIds: List<Int>?,
        var masterIds: List<Int>?
): Serializable {
    constructor() : this(null, null, null, null, null, null,
            null, null, null, null, null)
}