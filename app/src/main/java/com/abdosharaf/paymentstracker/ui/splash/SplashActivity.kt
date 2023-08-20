package com.abdosharaf.paymentstracker.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.abdosharaf.paymentstracker.base.BaseActivity
import com.abdosharaf.paymentstracker.databinding.ActivitySplashBinding
import com.abdosharaf.paymentstracker.ui.home.HomeActivity
import com.abdosharaf.paymentstracker.ui.oldMain.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
//            Intent(this, MainActivity::class.java).also { intent ->
            Intent(this, HomeActivity::class.java).also { intent ->
                startActivity(intent)
                finish()
            }
        }, 1500L)
    }
}