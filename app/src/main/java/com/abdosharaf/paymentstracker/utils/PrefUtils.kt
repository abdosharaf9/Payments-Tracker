package com.abdosharaf.paymentstracker.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import kotlinx.coroutines.flow.first
import java.lang.Double.doubleToLongBits
import java.lang.Double.doubleToRawLongBits
import java.lang.Double.longBitsToDouble
import java.lang.ref.WeakReference

/**
 * This method is used to save values using DataStore as key-value data.
 * @param dataStore DataStore instance that will be used to save the data.
 * @param key The key used to save data.
 * @param value The value you need to save. */
suspend fun saveToDataStore(dataStore: DataStore<Preferences>, key: String, value: Double) {
    // TODO: Fix the preferences type error!!!
    val dataStoreKey = preferencesKey<Double>(key)
    dataStore.edit { settings ->
        settings[dataStoreKey] = value
    }
}


/**
 * This method is used to get values saved in the DataStore using the key.
 * @param dataStore DataStore instance that will be used to get the data.
 * @param key The key that was used to save data. */
suspend fun readFromDataStore(dataStore: DataStore<Preferences>, key: String): Double? {
    // TODO: Fix the preferences type error!!!
    val dataStoreKey = preferencesKey<Double>(key)
    val preferences = dataStore.data.first()
    return preferences[dataStoreKey]
}


/**
 * This method is used to save a value into the Default Shared Preferences file using a unique key.
 * @param context The context is used to get the Default Shared Preferences.
 * @param key This key is used to save the value.
 * @param value This is the value you want to save.*/
fun saveToPrefs(context: Context?, key: String?, value: Any?) {
    val contextWeakReference = WeakReference(context)
    if (contextWeakReference.get() != null) {
        val prefs = getDefaultSharedPreferences(contextWeakReference.get()!!)
        val editor = prefs.edit()

        when (value) {
            is Int -> editor.putInt(key, value.toInt())
            is String -> editor.putString(key, value.toString())
            is Boolean -> editor.putBoolean(key, value.toString().toBoolean())
            is Long -> editor.putLong(key, value.toLong())
            is Float -> editor.putFloat(key, value.toFloat())
            is Double -> editor.putLong(key, doubleToRawLongBits(value))
        }
        editor.apply()
    }
}


/**
 * This method is used to get a saved value from the Default Shared Preferences file using its key.
 * @param context The context is used to get the Default Shared Preferences.
 * @param key This key is used to get the value.
 * @param defaultValue This is the default value you will get if there is no value for this key.*/
fun getFromPrefs(context: Context?, key: String?, defaultValue: Any): Any? {
    val contextWeakReference = WeakReference(context)
    return if (contextWeakReference.get() != null) {
        val sharedPrefs = getDefaultSharedPreferences(contextWeakReference.get()!!)

        try {
            when (defaultValue) {
                is String -> sharedPrefs.getString(key, defaultValue.toString())

                is Int -> sharedPrefs.getInt(key, defaultValue.toInt())

                is Boolean -> sharedPrefs.getBoolean(key, defaultValue.toString().toBoolean())

                is Long -> sharedPrefs.getLong(key, defaultValue.toLong())

                is Float -> sharedPrefs.getFloat(key, defaultValue.toFloat())

                is Double -> longBitsToDouble(
                    sharedPrefs.getLong(
                        key,
                        doubleToLongBits(defaultValue)
                    )
                )

                else -> defaultValue
            }
        } catch (e: Exception) {
            defaultValue
        }
    } else defaultValue
}


/**
 * This method is used to delete a value from the Default Shared Preferences file using its key.
 * @param context The context is used to get the Default Shared Preferences.
 * @param key This key is used to delete the value.*/
fun removeFromPrefs(context: Context?, key: String?) {
    val contextWeakReference = WeakReference(context)
    if (contextWeakReference.get() != null) {
        val prefs = getDefaultSharedPreferences(contextWeakReference.get()!!)
        val editor = prefs.edit()
        editor.remove(key)
        editor.apply()
    }
}


// TODO: Test 3wiida functions!!!
/*suspend fun <v> saveToDataStore(dataStore: DataStore<Preferences>, key: String, value: v) {
    when (value) {
        is String -> {
            val k = stringPreferencesKey(key)
            dataStore.edit { it[k] = value }
        }

        is Int -> {
            val k = intPreferencesKey(key)
            dataStore.edit { it[k] = value }
        }

        is Long -> {
            val k = longPreferencesKey(key)
            dataStore.edit { it[k] = value }
        }

        is Boolean -> {
            val k = booleanPreferencesKey(key)
            dataStore.edit { it[k] = value }
        }
    }
}*/

/*fun <v> readFromDataStore(dataStore: DataStore<Preferences>, key: String, defValue: v): Flow<v> {
    return when (defValue) {
        is String -> {
            val k = stringPreferencesKey(key)
            dataStore.data.map { (it[k] as v ?: defValue) }
        }

        is Int -> {
            val k = intPreferencesKey(key)
            dataStore.data.map { (it[k] as v ?: defValue) }
        }

        is Long -> {
            val k = longPreferencesKey(key)
            dataStore.data.map { (it[k] as v ?: defValue) }
        }

        is Boolean -> {
            val k = booleanPreferencesKey(key)
            dataStore.data.map { (it[k] as v ?: defValue) }
        }

        else -> {
            dataStore.data.map { defValue }
        }
    }
}*/

/*
fun stringPreferencesKey(key: String) = preferencesKey<String>(key)
fun intPreferencesKey(key: String) = preferencesKey<Int>(key)
fun longPreferencesKey(key: String) = preferencesKey<Long>(key)
fun booleanPreferencesKey(key: String) = preferencesKey<Boolean>(key)*/
