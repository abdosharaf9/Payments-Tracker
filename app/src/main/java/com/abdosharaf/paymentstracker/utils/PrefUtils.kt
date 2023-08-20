package com.abdosharaf.paymentstracker.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import kotlinx.coroutines.flow.first

/**
 * This method is used to save values using DataStore as key-value data.
 * @param dataStore DataStore instance that will be used to save the data.
 * @param key The key used to save data.
 * @param value The value you need to save. */
suspend fun saveToPref(dataStore: DataStore<Preferences>, key: String, value: Any) {
    val dataStoreKey = preferencesKey<Any>(key)
    dataStore.edit { settings ->
        settings[dataStoreKey] = value
    }
}

/**
 * This method is used to get values saved in the DataStore using the key.
 * @param dataStore DataStore instance that will be used to get the data.
 * @param key The key that was used to save data. */
suspend fun readFromPref(dataStore: DataStore<Preferences>, key: String): Any? {
    val dataStoreKey = preferencesKey<Any>(key)
    val preferences = dataStore.data.first()
    return preferences[dataStoreKey]
}