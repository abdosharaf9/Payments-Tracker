package com.abdosharaf.paymentstracker.ui.addExpense

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.abdosharaf.paymentstracker.base.BaseViewModel
import com.abdosharaf.paymentstracker.models.PaymentItem
import com.abdosharaf.paymentstracker.utils.FormErrors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(private val repository: AddExpenseRepository) :
    BaseViewModel() {

    val errorList = ObservableArrayList<FormErrors>()

    fun addNewExpense(item: PaymentItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewExpense(item)
        }
    }

    fun validateForm(name: String, value: String): Boolean {
        errorList.clear()

        if(name.isEmpty()) errorList.add(FormErrors.INVALID_NAME)
        if(value.isEmpty()) errorList.add(FormErrors.INVALID_VALUE)

        return errorList.isEmpty()
    }
}