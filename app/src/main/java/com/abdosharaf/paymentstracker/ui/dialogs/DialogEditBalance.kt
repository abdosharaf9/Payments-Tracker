package com.abdosharaf.paymentstracker.ui.dialogs

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.abdosharaf.paymentstracker.databinding.DialogEditBalanceBinding

class DialogEditBalance(
    private val clickListener: ((Double) -> Unit),
    context: Context
) : Dialog(context) {

    private lateinit var binding: DialogEditBalanceBinding

    init {
        showDialog()
    }

    private fun showDialog() {
        binding = DialogEditBalanceBinding.inflate(LayoutInflater.from(context))

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        if (window != null) {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            window!!.setLayout(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            val back = ColorDrawable(Color.TRANSPARENT)
            val margin = 50
            val inset = InsetDrawable(back, margin)
            window!!.setBackgroundDrawable(inset)
        }

        binding.btnEditBalance.setOnClickListener {
            val newBalance = binding.etBalance.text.toString().toDouble()
            clickListener.invoke(newBalance)
            this.dismiss()
        }

        show()
    }
}