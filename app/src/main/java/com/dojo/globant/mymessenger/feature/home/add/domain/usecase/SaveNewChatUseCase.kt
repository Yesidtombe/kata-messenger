package com.dojo.globant.mymessenger.feature.home.add.domain.usecase

import com.dojo.globant.mymessenger.feature.home.add.data.model.Contact
import com.dojo.globant.mymessenger.feature.home.add.data.repository.SaveChatRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SaveNewChatUseCase(
    private val repository: SaveChatRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun saveChat(contact: Contact): Flow<Boolean> {
        return flow {
            repository.addNewMessage(contact)
            emit(true)
        }.catch {
            emit(false)
        }.flowOn(dispatcher)
    }
}