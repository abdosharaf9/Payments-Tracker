package com.abdosharaf.paymentstracker.utils

import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import java.lang.Double.doubleToLongBits
import java.lang.Double.doubleToRawLongBits
import java.lang.Double.longBitsToDouble
import java.lang.ref.WeakReference

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