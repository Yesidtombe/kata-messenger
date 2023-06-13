package com.dojo.globant.mymessenger.feature.chat.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mymessenger.core.datastore.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userManager: UserManager
) : ViewModel() {

    fun isUserLogged(
        onNavigateToLoginScreen: () -> Unit,
        onNavigateToHomeScreen: () -> Unit
    ) {
        viewModelScope.launch {
            when (userManager.getIsLogged()) {
                true -> {
                    onNavigateToHomeScreen()
                }
                false -> {
                    onNavigateToLoginScreen()
                }
            }
        }
    }
}