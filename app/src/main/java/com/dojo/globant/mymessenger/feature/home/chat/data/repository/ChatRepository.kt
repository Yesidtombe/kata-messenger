package com.dojo.globant.mymessenger.feature.home.chat.data.repository

import com.dojo.globant.mymessenger.feature.home.chat.data.model.Message
import com.google.firebase.firestore.CollectionReference
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@ViewModelScoped
class ChatRepository @Inject constructor(
    private val messageList: CollectionReference
) {

    fun addNewMessage(message: Message) {
        try {
            messageList.document(message.id).set(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getMessageList(): Flow<List<Message>> = flow {
        val messages = messageList.get().await().map { document ->
            document.toObject(Message::class.java)
        }
        emit(messages)
    }.catch {
        emit(listOf())
    }
}