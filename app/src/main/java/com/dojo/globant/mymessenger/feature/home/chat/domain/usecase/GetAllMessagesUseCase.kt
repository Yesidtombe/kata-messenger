package com.dojo.globant.mymessenger.feature.home.chat.domain.usecase

import com.dojo.globant.mymessenger.core.datastore.UserManager
import com.dojo.globant.mymessenger.feature.home.chat.data.model.Message
import com.dojo.globant.mymessenger.feature.home.chat.data.repository.ChatRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetAllMessagesUseCase(
    private val repository: ChatRepository,
    private val userManager: UserManager,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getAllMessages(): Flow<List<Message>> =
        repository.getMessageList().flowOn(dispatcher)
}