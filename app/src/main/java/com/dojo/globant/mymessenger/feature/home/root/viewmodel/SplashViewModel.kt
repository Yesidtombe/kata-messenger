package com.dojo.globant.mymessenger.feature.home.root.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mymessenger.feature.sign_in.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    fun isUserLogged(
        onNavigateToLoginScreen: () -> Unit,
        onNavigateToHomeScreen: () -> Unit
    ) {
        viewModelScope.launch {
            signInUseCase.isUserLogged().collect { result ->
                if (result)
                    onNavigateToHomeScreen()
                else
                    onNavigateToLoginScreen()
            }
        }
    }
}