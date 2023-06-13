package com.dojo.globant.mymessenger.feature.home.chat.data.repository

import com.dojo.globant.mymessenger.feature.home.chat.data.model.Message
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val messageList: CollectionReference
) {

    fun addMessage(message: Message, idReceiver: Int) {
        try {
            messageList.document(idReceiver.toString()).set(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}