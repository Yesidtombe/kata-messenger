package com.dojo.globant.mymessenger.feature.home.list.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mymessenger.feature.home.add.data.model.Contact
import com.dojo.globant.mymessenger.feature.home.list.domain.usecase.GetAllChatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getAllChatsUseCase: GetAllChatsUseCase
) : ViewModel() {

    private val _listChatState: MutableList<Contact> = mutableStateListOf()
    val listChatState: MutableList<Contact>
        get() = _listChatState

    init {
        viewModelScope.launch {
            getAllChatsUseCase.getAllChats().collect { result ->
                _listChatState.clear()
                _listChatState.addAll(result)
            }
        }
    }

}