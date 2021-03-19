package com.game.database.rawg.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.game.database.rawg.R
import com.game.database.rawg.ui.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setTransparentStatusBar()

        lifecycleScope.launch {
            delay(500)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }

    }

    private fun setTransparentStatusBar() {
        val window: Window = window
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

}