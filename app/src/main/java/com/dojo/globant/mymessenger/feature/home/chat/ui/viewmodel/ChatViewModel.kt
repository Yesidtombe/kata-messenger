package com.dojo.globant.mymessenger.feature.home.chat.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dojo.globant.mymessenger.core.datastore.UserManager
import com.dojo.globant.mymessenger.feature.home.chat.data.model.Message
import com.dojo.globant.mymessenger.feature.home.chat.data.repository.ChatRepository
import com.dojo.globant.mymessenger.feature.home.chat.ui.ChatState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val userManager: UserManager
) : ViewModel() {

    private val _state: MutableState<ChatState> = mutableStateOf(ChatState())
    val state: State<ChatState>
        get() = _state

    fun newMessage(content: String) {
        val message = Message(
            id = UUID.randomUUID().toString(),
            content = content,
            from = "3112223344",
            to = "3234456677"
        )
        chatRepository.addMessage(message, 12345)
    }

}