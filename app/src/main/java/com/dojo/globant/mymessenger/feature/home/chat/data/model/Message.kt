package com.dojo.globant.mymessenger.feature.home.chat.data.model

import com.dojo.globant.mymessenger.feature.home.chat.ui.ChatState

data class Message(
    val id: String,
    val content: String,
    val from: String,
    val to: String
) {
    constructor(): this("", "", "", "")
}

fun Message.toChatState() = ChatState(
    message = this
)