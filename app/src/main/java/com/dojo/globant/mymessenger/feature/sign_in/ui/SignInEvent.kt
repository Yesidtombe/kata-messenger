package com.dojo.globant.mymessenger.feature.sign_in.ui

sealed class SignInEvent {
    data class EmailChanged(val email: String) : SignInEvent()
    data class PasswordChanged(val password: String) : SignInEvent()
    class OnSignIn(val goToHome: () -> Unit) : SignInEvent()
}