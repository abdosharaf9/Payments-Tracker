package com.abdosharaf.paymentstracker.ui.addExpense

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import com.abdosharaf.paymentstracker.PrefKeys.BALANCE_KEY
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.base.BaseActivity
import com.abdosharaf.paymentstracker.databinding.ActivityAddExpenseBinding
import com.abdosharaf.paymentstracker.models.PaymentItem
import com.abdosharaf.paymentstracker.utils.readFromDataStore
import com.abdosharaf.paymentstracker.utils.saveToDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddExpenseActivity : BaseActivity() {

    private lateinit var binding: ActivityAddExpenseBinding
    private val viewModel: AddExpenseViewModel by viewModels()
    private val dataStore: DataStore<Preferences> by lazy {
        createDataStore(name = "my_data_store")
    }

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
                lifecycleScope.launch {
                    viewModel.addNewExpense(PaymentItem(name, value, description))

                    /*val balance = readFromDataStore(dataStore, BALANCE_KEY)
                    balance?.let {
                        val newBalance = it.minus(value.toDouble())
                        saveToDataStore(dataStore, BALANCE_KEY, newBalance)
                    }*/

                    showSuccessToast("Added successfully")
                    finish()
                }
            }
        }

        binding.rgDate.setOnCheckedChangeListener { radioGroup, _ ->
            binding.datePicker.isVisible = radioGroup.checkedRadioButtonId != R.id.rbToday
        }
    }
}