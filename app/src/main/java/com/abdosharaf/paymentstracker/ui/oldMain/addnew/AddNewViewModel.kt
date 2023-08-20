package com.abdosharaf.paymentstracker.ui.oldMain.addnew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdosharaf.paymentstracker.models.PaymentItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewViewModel @Inject constructor(private val repository: AddNewRepository): ViewModel() {
    // TODO: Check form !!!!


    fun addNewItem(item: PaymentItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewPayment(item)
        }
    }
}