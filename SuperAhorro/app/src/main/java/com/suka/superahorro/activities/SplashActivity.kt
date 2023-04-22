package com.suka.superahorro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.suka.superahorro.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIMEOUT_MS : Long = 2000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
          }, SPLASH_TIMEOUT_MS)
    }
}