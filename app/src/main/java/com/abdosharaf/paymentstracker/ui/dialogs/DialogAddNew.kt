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
import com.abdosharaf.paymentstracker.databinding.DialogAddNewBinding

class DialogAddNew(
    private val incomeListener: (() -> Unit),
    private val expenseListener: (() -> Unit),
    context: Context
) : Dialog(context) {

    private lateinit var binding: DialogAddNewBinding

    init {
        showDialog()
    }

    private fun showDialog() {
        binding = DialogAddNewBinding.inflate(LayoutInflater.from(context))

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

        binding.incomeLinear.setOnClickListener {
            incomeListener.invoke()
            this.dismiss()
        }

        binding.expenseLinear.setOnClickListener {
            expenseListener.invoke()
            this.dismiss()
        }

        show()
    }
}