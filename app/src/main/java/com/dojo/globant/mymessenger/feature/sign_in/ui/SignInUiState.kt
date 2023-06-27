package com.dojo.globant.mymessenger.feature.sign_in.ui

import com.dojo.globant.mymessenger.common.util.UiText

data class SignInUiState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val isLoading: Boolean = false
)
