package com.abdosharaf.paymentstracker.ui.paymentslist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdosharaf.paymentstracker.models.PaymentItem
import com.abdosharaf.paymentstracker.repositories.PaymentsRepository

class PaymentsListViewModel(private val repository: PaymentsRepository, app: Application) : ViewModel() {

    private val _list = MutableLiveData<List<PaymentItem>>()
    val list : LiveData<List<PaymentItem>>
        get() = _list

    suspend fun setTheList() {
        _list.value = repository.getAllPayments()
    }

    suspend fun addPayment(item : PaymentItem) {
        repository.addPayment(item)
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }
}