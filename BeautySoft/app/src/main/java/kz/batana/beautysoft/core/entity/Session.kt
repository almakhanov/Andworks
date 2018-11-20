package kz.batana.beautysoft.core.entity

import java.io.Serializable

data class Session(
        var id: Int?,
        var institutionId: Int?,
        var masterId: Int?,
        var customerId: Int?,
        var date: String?,
        var price: Int?,
        var productIds: List<String>?,
        var photoIds: List<Int>?
) : Serializable{
    constructor() : this(null,null, null, null, null, null,
            null, null)
}