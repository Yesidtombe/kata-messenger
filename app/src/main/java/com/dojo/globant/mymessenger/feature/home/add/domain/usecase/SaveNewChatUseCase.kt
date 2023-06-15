package com.dojo.globant.mymessenger.feature.home.add.domain.usecase

import com.dojo.globant.mymessenger.core.datastore.UserManager
import com.dojo.globant.mymessenger.feature.home.add.data.model.Contact
import com.dojo.globant.mymessenger.feature.home.add.data.repository.SaveChatRepository
import com.dojo.globant.mymessenger.feature.home.list.data.model.Chat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*

class SaveNewChatUseCase(
    private val repository: SaveChatRepository,
    private val userManager: UserManager,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun saveChat(contact: Contact): Flow<Boolean> {
        return flow {
            val chat = Chat(
                id = contact.id,
                contact = contact,
                from = userManager.getUserPhone()
            )
            repository.addNewMessage(chat)
            emit(true)
        }.catch {
            emit(false)
        }.flowOn(dispatcher)
    }
}