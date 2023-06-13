package com.dojo.globant.mymessenger.feature.home.chat.data.model

data class Message(
    val id: String,
    val content: String,
    val from: String,
    val to: String
) {
    constructor(): this("", "", "", "")
}
