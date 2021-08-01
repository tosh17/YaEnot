package ru.yanot.practicum.presentation.onboarding.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.yanot.practicum.base.BaseViewModel
import ru.yanot.practicum.data.SharedPreferenceManager
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val sharedPreferenceManager: SharedPreferenceManager) : BaseViewModel() {

    fun setOnboadingSeen() {
        sharedPreferenceManager.setOnboadingSeen()
    }

    fun isOnboardingSeen(): Boolean{
        return sharedPreferenceManager.isOnboadingSeen()
    }
}