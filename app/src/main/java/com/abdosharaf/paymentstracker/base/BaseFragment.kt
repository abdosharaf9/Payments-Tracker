package com.abdosharaf.paymentstracker.base

import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    fun showSuccessToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        // TODO: Implement success toast
        Toast.makeText(requireContext(), message, duration).show()
    }
}