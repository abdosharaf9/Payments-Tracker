package com.abdosharaf.paymentstracker

import androidx.lifecycle.MutableLiveData

object Common {
    val balance: MutableLiveData<Double> = MutableLiveData(0.0)
}