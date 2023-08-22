package com.abdosharaf.paymentstracker.ui.addIncome

import android.os.Bundle
import com.abdosharaf.paymentstracker.base.BaseActivity
import com.abdosharaf.paymentstracker.databinding.ActivityAddIncomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddIncomeActivity : BaseActivity() {

    private lateinit var binding: ActivityAddIncomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}