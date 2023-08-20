package com.abdosharaf.paymentstracker.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    fun showSuccessToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        // TODO: Implement success toast
        Toast.makeText(this, message, duration).show()
    }

    fun showErrorToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        // TODO: Implement error toast
        Toast.makeText(this, message, duration).show()
    }

    fun showInternetDialog(message: String, duration: Int = Toast.LENGTH_SHORT) {
        // TODO: Implement Internet dialog
        Toast.makeText(this, message, duration).show()
    }
}