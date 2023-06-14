package com.dojo.globant.mymessenger.feature.sign_in.domain.usecase

import com.dojo.globant.mymessenger.feature.sign_in.data.repositories.SignInRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SignInUseCase(
    private val repository: SignInRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun signIn(phone: String): Flow<Boolean> =
        flow {
            repository.saveSession(phone)
            emit(true)
        }.catch {
            emit(false)
        }.flowOn(dispatcher)

    suspend fun isUserLogged() = repository.isUserLogged()

}