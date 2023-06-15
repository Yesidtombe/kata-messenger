package com.dojo.globant.mymessenger.feature.home.chat.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mymessenger.feature.home.chat.data.model.toChatState
import com.dojo.globant.mymessenger.feature.home.chat.domain.usecase.GetAllMessagesUseCase
import com.dojo.globant.mymessenger.feature.home.chat.domain.usecase.SendMessageUseCase
import com.dojo.globant.mymessenger.feature.home.chat.ui.ChatState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getAllMessagesUseCase: GetAllMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
) : ViewModel() {

    private val _messageState = mutableStateOf("")
    val messageState: MutableState<String> get() = _messageState

    private val _listChatState: MutableList<ChatState> = mutableStateListOf()
    val listChatState: MutableList<ChatState>
        get() = _listChatState

    init {
        viewModelScope.launch {
            getAllMessagesUseCase.getAllMessages().collect { result ->
                _listChatState.clear()
                _listChatState.addAll(result.map { it.toChatState() })
            }
        }
    }

    fun newMessage(content: String, idRecipient: Int) {
        viewModelScope.launch {
            sendMessageUseCase.sendMessage(content, idRecipient.toString()).collect { result ->
                if (result.id.isBlank())
                    _listChatState.add(ChatState())
                else {
                    _messageState.value = ""
                    _listChatState.add(ChatState(message = result))
                }

            }
        }
    }

    fun onMessageChanged(value: String) {
        _messageState.value = value
    }

}