package com.abdosharaf.paymentstracker.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.abdosharaf.paymentstracker.databinding.ActivitySplashBinding
import com.abdosharaf.paymentstracker.ui.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, MainActivity::class.java).also { intent ->
                startActivity(intent)
                finish()
            }
        }, 1500L)
    }
}