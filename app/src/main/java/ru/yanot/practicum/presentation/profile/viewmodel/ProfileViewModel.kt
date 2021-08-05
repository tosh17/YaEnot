package ru.yanot.practicum.presentation.profile.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yanot.practicum.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : BaseViewModel() {

    private val _avatarUrl: MutableStateFlow<String?> = MutableStateFlow(null)
    val avatarUrl: StateFlow<String?> get() = _avatarUrl.asStateFlow()

    private val _userName: MutableStateFlow<String?> = MutableStateFlow(null)
    val userName: StateFlow<String?> get() = _userName.asStateFlow()

    init {
        _userName.value = "I'm Enot, you are not"
        viewModelScope.launch {
            delay(6_000)
            _avatarUrl.value =
                "https://proprikol.ru/wp-content/uploads/2019/09/prikolnye-foto-enotov-15.jpg"
        }
    }
}