package com.abdosharaf.paymentstracker.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.abdosharaf.paymentstracker.utils.DataStoreKeys.BALANCE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {

    fun readBalance(): Flow<Double?> {
        return dataStore.data.map { prefs ->
            prefs[BALANCE_KEY]
        }
    }

    suspend fun saveBalance(balance: Double) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[BALANCE_KEY] = balance
        }
    }
}