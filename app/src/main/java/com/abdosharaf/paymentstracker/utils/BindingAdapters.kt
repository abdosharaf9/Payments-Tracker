package com.abdosharaf.paymentstracker.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.abdosharaf.paymentstracker.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@BindingAdapter("parseDate")
fun TextView.parseDate(date: Long) {
    this.text = try {
        SimpleDateFormat("yyyy-MM-dd '${this.context.getString(R.string.at)}' hh:mm a", Locale.ENGLISH).format(Date(date))
    } catch (e: Exception) {
        e.printStackTrace()
        "--"
    }
}