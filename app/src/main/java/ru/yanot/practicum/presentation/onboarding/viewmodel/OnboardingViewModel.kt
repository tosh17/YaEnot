package ru.yanot.practicum.presentation.onboarding.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.yanot.practicum.base.BaseViewModel
import ru.yanot.practicum.data.SharedPreferenceManager
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager) :
    BaseViewModel() {

    private val _isLastPage = MutableStateFlow(false)
    val isLastPage = _isLastPage
    private val _isSwipeStarted = MutableStateFlow(false)
    val isSwipeStarted = _isSwipeStarted

    fun setOnboadingSeen() {
        sharedPreferenceManager.setOnboadingSeen()
    }

    fun isOnboardingSeen(): Boolean {
        return sharedPreferenceManager.isOnboadingSeen()
    }
}