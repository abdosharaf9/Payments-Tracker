package com.abdosharaf.paymentstracker.ui.addPayment.addExpense

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.abdosharaf.paymentstracker.Common
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.base.BaseFragment
import com.abdosharaf.paymentstracker.databinding.FragmentAddExpenseBinding
import com.abdosharaf.paymentstracker.models.ExpenseItem
import com.abdosharaf.paymentstracker.utils.PrefKeys.BALANCE_KEY
import com.abdosharaf.paymentstracker.utils.saveToPrefs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class AddExpenseFragment : BaseFragment() {

    private lateinit var binding: FragmentAddExpenseBinding
    private val viewModel: AddExpenseViewModel by viewModels()
    private var isNow = true
    private var chosenDateString = ""
    private var chosenTimeString = ""
    private val calendar: Calendar by lazy { Calendar.getInstance() }
    private val datePicker: DatePickerDialog by lazy {
        DatePickerDialog(
            requireContext(), { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                chosenDateString = String.format("$year-%02d-$dayOfMonth", month + 1)
                binding.tvChosenDate.text = chosenDateString
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
    private val timePicker: TimePickerDialog by lazy {
        TimePickerDialog(
            requireContext(), { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                chosenTimeString =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(calendar.timeInMillis)
                binding.tvChosenTime.text = chosenTimeString
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddExpenseBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        initMainClicks()
        return binding.root
    }

    private fun initMainClicks() {
        binding.btnBack.setOnClickListener {
            requireActivity().finish()
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
                    addExpense(
                        name,
                        value,
                        description,
                        if (isNow) System.currentTimeMillis() else calendar.timeInMillis
                    )
                }
            }
        }

        binding.rgDate.setOnCheckedChangeListener { radioGroup, _ ->
            isNow = radioGroup.checkedRadioButtonId == R.id.rbNow
            binding.tvChosenDate.isVisible = radioGroup.checkedRadioButtonId != R.id.rbNow
            binding.tvChosenTime.isVisible = radioGroup.checkedRadioButtonId != R.id.rbNow

            if (isNow) {
                binding.tvChosenDate.text = getString(R.string.choose_a_date)
                binding.tvChosenTime.text = getString(R.string.choose_a_time)
            }
        }

        binding.tvChosenDate.setOnClickListener { datePicker.show() }

        binding.tvChosenTime.setOnClickListener { timePicker.show() }
    }

    private fun addExpense(name: String, value: String, description: String, date: Long) {
        lifecycleScope.launch {
            viewModel.addNewExpense(
                ExpenseItem(
                    name = name,
                    value = value,
                    desc = description,
                    date = date
                )
            )
            val newBalance = Common.balance.value?.minus(value.toDouble())
            Common.balance.value = newBalance
            saveToPrefs(requireContext(), BALANCE_KEY, newBalance)
            showSuccessToast(getString(R.string.added_successfully))
            requireActivity().finish()
        }
    }
}