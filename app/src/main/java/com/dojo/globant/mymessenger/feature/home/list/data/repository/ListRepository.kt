package com.dojo.globant.mymessenger.feature.home.list.data.repository

import com.dojo.globant.mymessenger.feature.home.add.data.model.Contact
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
    suspend fun getChatList(): Flow<List<Contact>> = flow {
        val chats = chatList.get().await().map { document ->
            document.toObject(Contact::class.java)
        }
        emit(chats)
    }.catch {
        emit(listOf())
    }
}