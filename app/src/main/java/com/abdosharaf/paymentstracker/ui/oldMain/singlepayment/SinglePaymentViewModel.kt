package com.abdosharaf.paymentstracker.ui.oldMain.singlepayment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdosharaf.paymentstracker.models.PaymentItem
import com.abdosharaf.paymentstracker.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SinglePaymentViewModel @Inject constructor(private val repository: DatabaseRepository) : ViewModel() {

    val item = MutableLiveData<PaymentItem>()

    private val _deleteItem = MutableLiveData(false)
    val deleteItem : LiveData<Boolean>
        get() = _deleteItem

    private val _deleteItemComplete = MutableLiveData(false)
    val deleteItemComplete : LiveData<Boolean>
        get() = _deleteItemComplete

    fun getItem(id: Long) {
        viewModelScope.launch {
            item.value = repository.getPaymentItem(id)
        }
    }

    fun deleteItem() {
        _deleteItem.value = true
    }

    fun deleteItemFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(item.value!!)
        }
        _deleteItemComplete.value = true
    }
}