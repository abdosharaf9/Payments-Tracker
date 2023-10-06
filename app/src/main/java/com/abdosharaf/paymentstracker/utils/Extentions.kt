package com.abdosharaf.paymentstracker.utils

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.abdosharaf.paymentstracker.Constants.DATA_STORE_NAME

val Context.appDataStore by preferencesDataStore(name = DATA_STORE_NAME)