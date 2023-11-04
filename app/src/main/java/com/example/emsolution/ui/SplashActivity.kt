package com.example.emsolution.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.emsolution.R
import com.example.emsolution.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        goToLogin()
        splashScreen.setKeepOnScreenCondition { false }
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

}
