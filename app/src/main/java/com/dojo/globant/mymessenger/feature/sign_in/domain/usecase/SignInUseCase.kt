package com.dojo.globant.mymessenger.feature.sign_in.domain.usecase

import com.dojo.globant.mymessenger.feature.sign_in.data.repositories.SignInRepository
import kotlinx.coroutines.CoroutineDispatcher

class SignInUseCase(
    private val repository: SignInRepository
) {
    suspend fun signIn(phone: String) {
        repository.saveSession(phone)
    }
}