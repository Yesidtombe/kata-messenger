package com.dojo.globant.mymessenger.feature.home.profile.domain.usecase

import com.dojo.globant.mymessenger.feature.home.profile.data.repository.GetSessionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetSessionUseCase(
    private val repository: GetSessionRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getSessionUser(): Flow<String> =
        flow {
            emit(repository.getPhone())
        }.catch {
            emit("")
        }.flowOn(dispatcher)
}