package ru.yanot.practicum.presentation.auth_screen

import java.lang.Exception

sealed class AuthState {
    object EMPTY : AuthState()
    object LOADING : AuthState()
    class ERROR(val exception: Exception) : AuthState()
    object SUCCESS : AuthState()
}
