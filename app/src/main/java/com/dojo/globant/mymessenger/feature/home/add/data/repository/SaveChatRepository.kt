package com.dojo.globant.mymessenger.feature.home.add.data.repository

import com.dojo.globant.mymessenger.feature.home.list.data.model.Chat
import com.google.firebase.firestore.CollectionReference
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Named

@ViewModelScoped
class SaveChatRepository @Inject constructor(
    @Named("chats") private val chatList: CollectionReference
) {
    fun addNewMessage(chat: Chat) {
        try {
            chatList.document(chat.id).set(chat)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}