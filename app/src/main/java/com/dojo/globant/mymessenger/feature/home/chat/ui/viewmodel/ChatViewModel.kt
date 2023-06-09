package com.dojo.globant.mymessenger.feature.home.chat.ui.viewmodel

import androidx.compose.runtime.State
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
    val messageState: State<String> get() = _messageState

    private val _listMessageState: MutableList<ChatState> = mutableStateListOf()
    val listMessageState: List<ChatState>
        get() = _listMessageState

    fun getAllMessages(to: String) {
        viewModelScope.launch {
            getAllMessagesUseCase.getAllMessages(to).collect { result ->
                _listMessageState.clear()
                _listMessageState.addAll(result.sortedBy { it.time }.map { it.toChatState() })
            }
        }
    }

    fun newMessage(content: String, idRecipient: String) {
        viewModelScope.launch {
            sendMessageUseCase.sendMessage(content, idRecipient).collect { result ->
                if (result.id.isBlank())
                    _listMessageState.add(ChatState())
                else {
                    _messageState.value = ""
                    _listMessageState.add(result.toChatState())
                }

            }
        }
    }

    fun onMessageChanged(value: String) {
        _messageState.value = value
    }

}