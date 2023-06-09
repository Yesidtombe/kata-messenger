package com.dojo.globant.mymessenger.feature.sign_in.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mymessenger.feature.sign_in.domain.usecase.SignInUseCase
import com.dojo.globant.mymessenger.feature.sign_in.domain.usecase.ValidateFieldsUseCase
import com.dojo.globant.mymessenger.feature.sign_in.ui.SignInEvent
import com.dojo.globant.mymessenger.feature.sign_in.ui.SignInUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validateFieldsUseCase: ValidateFieldsUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    var state by mutableStateOf(SignInUiState())
        private set

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.PhoneChanged -> {
                state = state.copy(phone = event.phone)
            }
            is SignInEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is SignInEvent.OnSignIn -> {
                validateData(event.goToHome)
            }
        }
    }

    private fun validateData(goToHome: () -> Unit) {
        val phoneResult = validateFieldsUseCase.validatePhone(state.phone)
        val passwordResult = validateFieldsUseCase.validatePassword(state.password)
        val hasError = listOf(
            phoneResult,
            passwordResult
        ).any { !it.successful }

        state = state.copy(
            phoneError = phoneResult.errorMessage,
            passwordError = passwordResult.errorMessage
        )

        if (hasError)
            return

        viewModelScope.launch {
            signInUseCase.signIn(state.phone)
            goToHome()
        }
    }

}