package com.abdosharaf.paymentstracker.ui.addExpense

import android.os.Bundle
import androidx.activity.viewModels
import com.abdosharaf.paymentstracker.base.BaseActivity
import com.abdosharaf.paymentstracker.databinding.ActivityAddExpenseBinding
import com.abdosharaf.paymentstracker.models.PaymentItem
import dagger.hilt.android.AndroidEntryPoint

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
            val name = binding.tieName.text.toString()
            val value = binding.tieValue.text.toString()
            val description = binding.tieDescription.text.toString()

            if (viewModel.validateForm(name, value)) {
                viewModel.addNewExpense(PaymentItem(name, value, description))
                showSuccessToast("Added successfully")
                finish()
            }
        }
    }
}