package com.dojo.globant.mymessenger.feature.sign_in.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mymessenger.common.util.Resource
import com.dojo.globant.mymessenger.feature.sign_in.domain.usecase.SignInUseCase
import com.dojo.globant.mymessenger.feature.sign_in.domain.usecase.ValidateFieldsUseCase
import com.dojo.globant.mymessenger.feature.sign_in.ui.SignInEvent
import com.dojo.globant.mymessenger.feature.sign_in.ui.SignInUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validateFieldsUseCase: ValidateFieldsUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    var state by mutableStateOf(SignInUiState())
        private set

    var isError by mutableStateOf(false)
        private set

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged -> {
                state = state.copy(email = event.email)
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
        val emailResult = validateFieldsUseCase.validateEmail(state.email)
        val passwordResult = validateFieldsUseCase.validatePassword(state.password)
        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }

        state = state.copy(
            emailError = emailResult.errorMessage,
            passwordError = passwordResult.errorMessage
        )

        if (hasError)
            return

        signIn(goToHome)
    }

    private fun signIn(goToHome: () -> Unit) {
        viewModelScope.launch {
            signInUseCase.signIn(state.email, state.password).catch {
                state = state.copy(
                    isLoading = false
                )
                isError = true
            }.collect { result ->
                when(result) {
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                        isError = true
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        goToHome()
                    }
                }
            }
        }
    }

    fun hideMessageError() {
        isError = false
    }

}