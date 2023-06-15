package com.dojo.globant.mymessenger.feature.home.chat.domain.usecase

import com.dojo.globant.mymessenger.core.datastore.UserManager
import com.dojo.globant.mymessenger.feature.home.chat.data.model.Message
import com.dojo.globant.mymessenger.feature.home.chat.data.repository.ChatRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*

class SendMessageUseCase(
    private val repository: ChatRepository,
    private val userManager: UserManager,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun sendMessage(content: String, idRecipient: String): Flow<Message> {
        val message = Message(
            id = UUID.randomUUID().toString(),
            content = content,
            from = userManager.getUserPhone(),
            to = idRecipient,
            time = Date().time
        )
        return flow {
            repository.addNewMessage(message)
            emit(message)
        }.catch {
            emit(Message())
        }.flowOn(dispatcher)
    }
}