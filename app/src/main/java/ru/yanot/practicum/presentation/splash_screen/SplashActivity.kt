package ru.yanot.practicum.presentation.splash_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import ru.yanot.practicum.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({ finish() }, DELAY_TIME)
    }

    companion object {
        private const val DELAY_TIME = 2000L
    }
}