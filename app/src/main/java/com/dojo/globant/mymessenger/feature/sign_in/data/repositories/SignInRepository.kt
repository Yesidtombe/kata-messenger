package com.dojo.globant.mymessenger.feature.sign_in.data.repositories

import com.dojo.globant.mymessenger.core.datastore.UserManager
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class SignInRepository @Inject constructor(
    private val userManager: UserManager
) {
    suspend fun saveSession(phone: String) {
        userManager.saveUserPhone(phone)
        userManager.saveIsLogged(true)
    }

    suspend fun isUserLogged() = userManager.getIsLogged().first()
}