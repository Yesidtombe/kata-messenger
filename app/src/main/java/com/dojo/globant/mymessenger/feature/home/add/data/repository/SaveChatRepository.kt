package com.dojo.globant.mymessenger.feature.home.add.data.repository

import com.dojo.globant.mymessenger.feature.home.add.data.model.Contact
import com.google.firebase.firestore.CollectionReference
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Named

@ViewModelScoped
class SaveChatRepository @Inject constructor(
    @Named("chats") private val chatList: CollectionReference
) {
    fun addNewMessage(contact: Contact) {
        try {
            chatList.document(contact.id).set(contact)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}