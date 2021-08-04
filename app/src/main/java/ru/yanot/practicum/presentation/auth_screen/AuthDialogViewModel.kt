package ru.yanot.practicum.presentation.auth_screen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yanot.practicum.base.BaseViewModel

class AuthDialogViewModel : BaseViewModel() {
    private val _authStateflow = MutableStateFlow<AuthState>(AuthState.EMPTY)
    val authStateflow: StateFlow<AuthState> = _authStateflow.asStateFlow()

    fun setAuthState(state: AuthState) {
        _authStateflow.value = state
        viewModelScope.launch {
            delay(2500)
            _authStateflow.value = AuthState.SUCCESS
        }
    }
}