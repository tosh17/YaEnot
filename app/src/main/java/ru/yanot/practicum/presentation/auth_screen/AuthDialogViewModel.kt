package ru.yanot.practicum.presentation.auth_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.yanot.practicum.base.BaseViewModel

class AuthDialogViewModel : BaseViewModel() {
    private val authStateLiveData = MutableLiveData<AuthState>(AuthState.EMPTY)

    fun observeAuthState() = authStateLiveData

    fun setAuthState(state: AuthState) {
        authStateLiveData.value = state
        viewModelScope.launch(Dispatchers.IO) {
            delay(2500)
            authStateLiveData.postValue(AuthState.ERROR(IllegalStateException()))
        }
    }
}