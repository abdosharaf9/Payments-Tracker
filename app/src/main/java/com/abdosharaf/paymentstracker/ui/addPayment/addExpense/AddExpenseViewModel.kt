package com.abdosharaf.paymentstracker.ui.addPayment.addExpense

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.viewModelScope
import com.abdosharaf.paymentstracker.base.BaseViewModel
import com.abdosharaf.paymentstracker.models.ExpenseItem
import com.abdosharaf.paymentstracker.repository.DatabaseRepository
import com.abdosharaf.paymentstracker.utils.FormErrors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(private val repository: DatabaseRepository) :
    BaseViewModel() {

    val errorList = ObservableArrayList<FormErrors>()

    fun addNewExpense(item: ExpenseItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewExpense(item)
        }
    }

    fun validateForm(name: String?, value: String?): Boolean {
        errorList.clear()

        if(name.isNullOrEmpty()) errorList.add(FormErrors.INVALID_NAME)
        if(value.isNullOrEmpty()) errorList.add(FormErrors.INVALID_VALUE)

        return errorList.isEmpty()
    }
}