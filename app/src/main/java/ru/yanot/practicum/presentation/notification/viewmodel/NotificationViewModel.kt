package ru.yanot.practicum.presentation.notification.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import ru.yanot.practicum.base.BaseViewModel
import ru.yanot.practicum.data.SharedPreferenceManager
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager
): BaseViewModel() {

    private val _isOnNotification = MutableStateFlow(false)
    val isOnNotification = _isOnNotification.asStateFlow()

    private val _keyCourseNotification = MutableStateFlow("")
    val keyCourseNotification = _keyCourseNotification.asStateFlow().filter { it.isNotEmpty() }

    init {
        viewModelScope.launch {
            keyCourseNotification.collect {
                _isOnNotification.value = sharedPreferenceManager.isOnNotification(it)
            }
        }
    }

    fun setKeyCourseNotification(key: String){
        _keyCourseNotification.value = key
    }
}