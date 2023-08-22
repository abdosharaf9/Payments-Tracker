package com.abdosharaf.paymentstracker.ui.oldMain.paymentslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsListViewModel @Inject constructor(private val repository: PaymentsListRepository) : ViewModel() {

    val list = repository.getAllPayments()

    private val _itemsDeleted = MutableLiveData(false)
    val itemsDeleted: LiveData<Boolean>
        get() = _itemsDeleted

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
        _itemsDeleted.value = true
    }

    fun resetDelete() {
        _itemsDeleted.value = false
    }
}