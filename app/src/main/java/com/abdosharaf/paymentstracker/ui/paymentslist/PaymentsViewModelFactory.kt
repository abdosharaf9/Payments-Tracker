package com.abdosharaf.paymentstracker.ui.paymentslist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdosharaf.paymentstracker.repositories.PaymentsRepository

class PaymentsViewModelFactory(private val repository: PaymentsRepository, val app : Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PaymentsListViewModel(repository, app) as T
    }
}