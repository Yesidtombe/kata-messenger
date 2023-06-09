package com.dojo.globant.mymessenger.feature.chat.home

sealed class ViewState {
    object Loading: ViewState()
    object LoggedIn: ViewState()
    object NotLoggedIn: ViewState()
}