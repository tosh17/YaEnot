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

    private val _isSwipeStarted = MutableStateFlow(false)

    fun setLastPageFlag(isLastPage: Boolean){
        _isLastPage.value = isLastPage
    }

    fun canFinishOnboarding() = _isLastPage.value && _isSwipeStarted.value

    fun setSwipeStartedFlag(isSwipeStarted: Boolean){
        _isSwipeStarted.value = isSwipeStarted
    }

    fun setOnboadingSeen() {
        sharedPreferenceManager.setOnboadingSeen()
    }

    fun isOnboardingSeen(): Boolean {
        return sharedPreferenceManager.isOnboadingSeen()
    }
}