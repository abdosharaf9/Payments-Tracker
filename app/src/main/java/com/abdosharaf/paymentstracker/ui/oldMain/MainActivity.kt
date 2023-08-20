package com.abdosharaf.paymentstracker.ui.oldMain

import android.os.Bundle
import com.abdosharaf.paymentstracker.base.BaseActivity
import com.abdosharaf.paymentstracker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}