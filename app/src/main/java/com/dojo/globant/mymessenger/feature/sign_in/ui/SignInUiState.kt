package com.dojo.globant.mymessenger.feature.sign_in.ui

import com.dojo.globant.mymessenger.common.util.UiText

data class SignInUiState(
    val phone: String = "",
    val phoneError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null
)
