package ru.yanot.practicum.presentation.onboarding.viewmodel

import ru.yanot.practicum.base.BaseViewModel
import ru.yanot.practicum.data.SharedPreferenceManager

class OnboardingViewModel constructor(private val sharedPreferenceManager: SharedPreferenceManager) : BaseViewModel() {

    fun setOnboadingSeen() {
        sharedPreferenceManager.setOnboadingSeen()
    }

    fun isOnboardingSeen(): Boolean{
        return sharedPreferenceManager.isOnboadingSeen()
    }
}