package kz.batana.beautysoft.core.entity

import java.io.Serializable

data class Salon (
    var id: Int,
    var title : String,
    var date : String,
    var content : String,
    var imageUrl : String
): Serializable