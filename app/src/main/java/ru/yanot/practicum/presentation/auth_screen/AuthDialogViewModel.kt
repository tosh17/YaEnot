package ru.yanot.practicum.presentation.auth_screen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yanot.practicum.base.BaseViewModel

class AuthDialogViewModel : BaseViewModel() {
    private val _authStateLiveData = MutableStateFlow<AuthState>(AuthState.EMPTY)
    val authStateLiveData: StateFlow<AuthState> = _authStateLiveData.asStateFlow()

    fun setAuthState(state: AuthState) {
        _authStateLiveData.value = state
        viewModelScope.launch(Dispatchers.IO) {
            delay(2500)
            _authStateLiveData.value = AuthState.SUCCESS
        }
    }
}