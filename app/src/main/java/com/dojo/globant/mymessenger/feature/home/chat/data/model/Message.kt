package com.dojo.globant.mymessenger.feature.home.chat.data.model

import com.dojo.globant.mymessenger.feature.home.chat.ui.ChatState
import java.text.SimpleDateFormat
import java.util.*

data class Message(
    val id: String,
    val content: String,
    val from: String,
    val to: String,
    val time: Long
) {
    constructor(): this("", "", "", "", 0)
}

fun Message.toChatState(): ChatState {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return ChatState(
        message = this,
        time = sdf.format(this.time)
    )
}