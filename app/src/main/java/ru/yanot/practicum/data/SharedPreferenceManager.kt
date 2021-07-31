package ru.yanot.practicum.data

import android.content.Context
import android.content.SharedPreferences

interface SharedPreferenceManager{
    fun isLogin(): Boolean
    fun isOnboadingSeen(): Boolean
    fun setOnboadingSeen()
}

class SharedPreferenceManagerImpl constructor(private val context: Context) : SharedPreferenceManager{

    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(ONBOAR_PREFS, Context.MODE_PRIVATE)
    }

    override fun isLogin(): Boolean {
            return true
    }

    override fun isOnboadingSeen(): Boolean {
        return preferences.contains(PREF_ONBOADRING_SEEN)
    }

     override fun setOnboadingSeen() {
        preferences.edit().putBoolean(PREF_ONBOADRING_SEEN, true).apply()
    }

    companion object{
        const val ONBOAR_PREFS = "practicum_prefs"
        const val PREF_ONBOADRING_SEEN = "pref_onboadring_seen"
    }
}