package com.dojo.globant.mymessenger.feature.chat.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mymessenger.core.datastore.UserManager
import com.dojo.globant.mymessenger.feature.chat.home.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userManager: UserManager
) : ViewModel() {

    var viewState by mutableStateOf<ViewState>(ViewState.Loading)

    init {
        viewModelScope.launch {
            viewState = when (userManager.getIsLogged()) {
                true -> {
                    ViewState.LoggedIn
                }
                false -> {
                    ViewState.NotLoggedIn
                }
            }
        }
    }
}