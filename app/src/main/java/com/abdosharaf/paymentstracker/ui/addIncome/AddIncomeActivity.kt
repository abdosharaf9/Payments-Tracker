package com.abdosharaf.paymentstracker.ui.addIncome

import android.os.Bundle
import android.util.Log
import com.abdosharaf.paymentstracker.Constants.TAG
import com.abdosharaf.paymentstracker.base.BaseActivity
import com.abdosharaf.paymentstracker.databinding.ActivityAddIncomeBinding
import com.abdosharaf.paymentstracker.utils.PrefKeys.BALANCE_KEY
import com.abdosharaf.paymentstracker.utils.getFromPrefs
import com.abdosharaf.paymentstracker.utils.saveToPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddIncomeActivity : BaseActivity() {

    private lateinit var binding: ActivityAddIncomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMainClicks()
    }

    private fun initMainClicks() {
        binding.btnAdd.setOnClickListener {
            val value = binding.etIncome.text.toString().toDoubleOrNull()
            if (value != null) {
                val oldValue = getFromPrefs(this, BALANCE_KEY, 0.0) as Double
                val newValue = value + oldValue
                Log.d(TAG, "onCreate: typed value = $value")
                Log.d(TAG, "onCreate: old value = $oldValue")
                Log.d(TAG, "onCreate: new value = $newValue")
                saveToPrefs(this, BALANCE_KEY, newValue)
                finish()
            }
        }
    }
}