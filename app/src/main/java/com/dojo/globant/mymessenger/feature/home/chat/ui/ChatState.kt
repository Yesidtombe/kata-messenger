package com.dojo.globant.mymessenger.feature.home.chat.ui

import com.dojo.globant.mymessenger.common.util.UiText
import com.dojo.globant.mymessenger.feature.home.chat.data.model.Message

data class ChatState(
    val isLoading: Boolean = false,
    val message: Message? = null,
    val error: UiText? = null
)
