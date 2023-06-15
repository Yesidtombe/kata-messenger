package com.dojo.globant.mymessenger.feature.home.list.domain.usecase

import com.dojo.globant.mymessenger.core.datastore.UserManager
import com.dojo.globant.mymessenger.feature.home.add.data.model.Contact
import com.dojo.globant.mymessenger.feature.home.list.data.model.Chat
import com.dojo.globant.mymessenger.feature.home.list.data.repository.ListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetAllChatsUseCase(
    private val repository: ListRepository,
    private val userManager: UserManager,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getAllChats(): Flow<List<Chat>> =
        repository.getChatList(userManager.getUserPhone()).flowOn(dispatcher)
}