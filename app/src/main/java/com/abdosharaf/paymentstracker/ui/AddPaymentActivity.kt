package com.abdosharaf.paymentstracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.abdosharaf.paymentstracker.databinding.ActivityAddPaymentBinding
import com.abdosharaf.paymentstracker.db.PaymentDatabase
import com.abdosharaf.paymentstracker.models.PaymentItem
import com.abdosharaf.paymentstracker.repositories.PaymentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPaymentBinding
    private lateinit var appDB: PaymentDatabase
    private lateinit var repository: PaymentsRepository
    private lateinit var viewModel: PaymentsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDB = PaymentDatabase.getDatabase(this)
        repository = PaymentsRepository(appDB)
        val viewModelFactory = PaymentsViewModelFactory(repository, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[PaymentsListViewModel::class.java]

        // Save button
        binding.btnSave.setOnClickListener {
            when {
                // Check if the name and the value are empty
                TextUtils.isEmpty(binding.etName.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this, "Please Enter A Name!!", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(binding.etValue.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this, "Please Enter A Value!!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // get an instance to add it to the database
                    val payment = PaymentItem(
                        binding.etName.text.toString(),
                        binding.etValue.text.toString(),
                        binding.etDescription.text.toString(),
                        null
                    )

                    // Add the item to the database
                    lifecycleScope.launch(Dispatchers.IO) {
                        viewModel.addPayment(payment)
                    }
                    Toast.makeText(this, "Added successfully ♥", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

        // Cancel button
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}