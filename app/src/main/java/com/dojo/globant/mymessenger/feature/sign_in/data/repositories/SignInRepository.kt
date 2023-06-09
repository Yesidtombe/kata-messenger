package com.dojo.globant.mymessenger.feature.sign_in.data.repositories

import com.dojo.globant.mymessenger.core.datastore.UserManager
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SignInRepository @Inject constructor(
    private val userManager: UserManager
) {
    suspend fun saveSession(phone: String) {
        userManager.saveUserPhone(phone)
        userManager.saveIsLogged(true)
    }
}