package com.abdosharaf.paymentstracker.utils

import androidx.datastore.preferences.core.doublePreferencesKey

object DataStoreKeys {
    val BALANCE_KEY = doublePreferencesKey("balance")
}