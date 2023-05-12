package com.abdosharaf.paymentstracker.ui.singlepayment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SingleItemViewModel : ViewModel() {

    private val _flagDeleteItem = MutableLiveData<Boolean>()
    val flagDeleteItem : LiveData<Boolean>
        get() = _flagDeleteItem

    fun deleteItem(x : Boolean) {
        _flagDeleteItem.value = x
    }

}