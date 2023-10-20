package com.abdosharaf.paymentstracker.ui.addExpense

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.abdosharaf.paymentstracker.Common
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.base.BaseActivity
import com.abdosharaf.paymentstracker.databinding.ActivityAddExpenseBinding
import com.abdosharaf.paymentstracker.models.ExpenseItem
import com.abdosharaf.paymentstracker.utils.PrefKeys.BALANCE_KEY
import com.abdosharaf.paymentstracker.utils.saveToPrefs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddExpenseActivity : BaseActivity() {

    private lateinit var binding: ActivityAddExpenseBinding
    private val viewModel: AddExpenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        initMainClicks()
    }

    private fun initMainClicks() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val value = binding.etValue.text.toString()
            val description = binding.etDescription.text.toString()

            if (viewModel.validateForm(name, value)) {
                if (Common.balance.value == null || Common.balance.value?.equals(0.0) == true) {
                    showErrorToast(getString(R.string.you_are_flat_broke))
                } else if (Common.balance.value!! < value.toDouble()) {
                    showErrorToast(getString(R.string.this_will_exceed_your_balance))
                } else {
                    lifecycleScope.launch {
                        viewModel.addNewExpense(ExpenseItem(name, value, description))
                        val newBalance = Common.balance.value?.minus(value.toDouble())
                        Common.balance.value = newBalance
                        saveToPrefs(this@AddExpenseActivity, BALANCE_KEY, newBalance)
                        showSuccessToast("Added successfully")
                        finish()
                    }
                }
            }
        }

        /*binding.rgDate.setOnCheckedChangeListener { radioGroup, _ ->
            binding.datePicker.isVisible = radioGroup.checkedRadioButtonId != R.id.rbToday
        }*/
    }
}