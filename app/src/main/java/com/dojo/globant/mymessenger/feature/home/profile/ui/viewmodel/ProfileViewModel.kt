package com.dojo.globant.mymessenger.feature.home.profile.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mymessenger.feature.home.profile.domain.usecase.GetSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getSessionUseCase: GetSessionUseCase
) : ViewModel() {

    private val _phoneState = mutableStateOf("")
    val phoneState: MutableState<String> get() = _phoneState

    fun isUserLogged() {
        viewModelScope.launch {
            getSessionUseCase.getSessionUser().collect { phone ->
                _phoneState.value = phone
            }
        }
    }

}