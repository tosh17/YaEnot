package ru.yanot.practicum.data

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface SharedPreferenceManager{
    fun getToken(): String?
    fun clearToken()
    fun isLogin(): Boolean
    fun isOnboadingSeen(): Boolean
    fun setOnboadingSeen()
}

class SharedPreferenceManagerImpl @Inject constructor(@ApplicationContext private val context: Context) : SharedPreferenceManager{

    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(ONBOAR_PREFS, Context.MODE_PRIVATE)
    }

    override fun getToken(): String? {
        return preferences.getString(PREFS_AUTH_TOKEN, null)
    }

    override fun clearToken() {
        preferences.edit().remove(PREFS_AUTH_TOKEN).apply()
    }

    override fun isLogin(): Boolean {
        getToken()?.let {
            return true
        }
        return false
    }

    override fun isOnboadingSeen(): Boolean {
        return preferences.contains(PREF_ONBOADRING_SEEN)
    }

     override fun setOnboadingSeen() {
        preferences.edit().putBoolean(PREF_ONBOADRING_SEEN, true).apply()
    }

    companion object{
        const val ONBOAR_PREFS = "practicum_prefs"
        const val PREFS_AUTH_TOKEN = "prefs_auth_token"
        const val PREF_ONBOADRING_SEEN = "pref_onboadring_seen"
    }
}