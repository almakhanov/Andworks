package kz.batana.firebase_chat.model

data class ChatChannel(val userIds: MutableList<String>) {
    constructor() : this(mutableListOf())
}