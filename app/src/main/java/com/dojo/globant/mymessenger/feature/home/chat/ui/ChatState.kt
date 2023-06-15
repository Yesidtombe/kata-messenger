package com.dojo.globant.mymessenger.feature.home.chat.ui

import com.dojo.globant.mymessenger.common.util.UiText
import com.dojo.globant.mymessenger.feature.home.chat.data.model.Message

data class ChatState(
    val message: Message = Message(),
    val error: UiText? = null
)
