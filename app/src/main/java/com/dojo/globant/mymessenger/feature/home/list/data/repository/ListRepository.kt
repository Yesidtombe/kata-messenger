package com.dojo.globant.mymessenger.feature.home.list.data.repository

import com.dojo.globant.mymessenger.feature.home.list.data.model.Chat
import com.google.firebase.firestore.CollectionReference
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

@ViewModelScoped
class ListRepository @Inject constructor(
    @Named("chats") private val chatList: CollectionReference
) {
    suspend fun getChatList(from: String): Flow<List<Chat>> = flow {
        val chats = chatList.whereEqualTo("from", from).get().await().map { document ->
            document.toObject(Chat::class.java)
        }
        emit(chats)
    }.catch {
        emit(listOf())
    }
}