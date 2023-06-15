package com.dojo.globant.mymessenger.feature.home.profile.data.repository

import com.dojo.globant.mymessenger.core.datastore.UserManager
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetSessionRepository @Inject constructor(
    private val userManager: UserManager
) {
    suspend fun getPhone() = userManager.getUserPhone()
}