package com.dojo.globant.mymessenger.feature.home.add.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mymessenger.feature.home.add.data.model.Contact
import com.dojo.globant.mymessenger.feature.home.add.domain.usecase.GetAllContactsUseCase
import com.dojo.globant.mymessenger.feature.home.add.domain.usecase.SaveNewChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase,
    private val saveNewChatUseCase: SaveNewChatUseCase
) : ViewModel() {

    private val _contactsState = mutableStateListOf<Contact>()
    val contactsState: MutableList<Contact> get() = _contactsState

    fun getContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val contactsListAsync = async { getAllContactsUseCase.getAllPhoneContacts() }
            val contactNumbersAsync = async { getAllContactsUseCase.getAllContactNumbers() }

            val contacts = contactsListAsync.await()
            val contactNumbers = contactNumbersAsync.await()

            contacts.forEach {
                contactNumbers[it.id]?.let { numbers ->
                    it.numbers = numbers
                }
            }
            withContext(Dispatchers.Main) {
                _contactsState.clear()
                _contactsState.addAll(contacts)
            }
        }
    }

    fun saveNewChat(contact: Contact) {
        viewModelScope.launch {
            saveNewChatUseCase.saveChat(contact).collect{
            }
        }
    }

}