package kz.batana.beautysoft.core.entity

data class User(var username: String, var password: String) {
    constructor() : this("", "")
}