package com.abdosharaf.paymentstracker.ui

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.databinding.ActivityPaymentsListBinding
import com.abdosharaf.paymentstracker.db.PaymentDatabase
import com.abdosharaf.paymentstracker.repositories.PaymentsRepository
import kotlinx.coroutines.*

class PaymentsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentsListBinding
    private lateinit var appDB : PaymentDatabase
    private lateinit var adapter: PaymentsRvAdapter
    lateinit var repository: PaymentsRepository
    lateinit var viewModel: PaymentsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDB = PaymentDatabase.getDatabase(this)
        repository = PaymentsRepository(appDB)
        val viewModelFactory = PaymentsViewModelFactory(repository, application)
        viewModel =ViewModelProvider(this, viewModelFactory)[PaymentsListViewModel::class.java]

        adapter = PaymentsRvAdapter()
        binding.rvPayments.adapter = adapter
        binding.rvPayments.layoutManager = LinearLayoutManager(this@PaymentsListActivity)

        // observe the list changes
        viewModel.list.observe(this) {
            adapter.differ.submitList(viewModel.list.value)
        }

        // Go to the single item page
        adapter.onItemClicked = { item ->
            Intent(this, MainActivity::class.java).also {
                it.putExtra("item", item)
                startActivity(it)
            }
        }

        // Add new payment button
        binding.fbAddNewPayment.setOnClickListener {
            Intent(this, AddPaymentActivity::class.java).also {
                startActivity(it)
            }
        }

        // Dialog to delete all payments
        val dialog2 = AlertDialog.Builder(this)
            .setTitle("Alert!!")
            .setMessage("Are you sure you want to delete all payments?")
            .setIcon(R.drawable.ic_baseline_pan_tool_24)
            .setPositiveButton("Yes"){ _, _ ->
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.deleteAll()
                    withContext(Dispatchers.Main) {
                        viewModel.setTheList()
                    }
                }
                Toast.makeText(this, "You deleted all successfully ♥", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No") { _, _ ->
                // Nothing here
            }
            .create()

        // Delete all button
        binding.btnDeleteAll.setOnClickListener {
            lifecycleScope.launch {
                dialog2.show()
                viewModel.setTheList()
            }
        }
    }

    // Refresh the list when return to the activity
    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.setTheList()
        }
    }
}

/*val list = listOf(
            PaymentItem("pay 1", "10", "description", null),
            PaymentItem("pay 2", "20", "description", null),
            PaymentItem("pay 3", "30", "description", null),
            PaymentItem("pay 4", "40", "description", null),
            PaymentItem("pay 5", "50", "description", null),
            PaymentItem("pay 6", "60", "description", null),
            PaymentItem("pay 7", "70", "description", null),
            PaymentItem("pay 8", "80", "description", null),
            PaymentItem("pay 9", "90", "description", null),
            PaymentItem("pay 10", "100", "description", null),
            PaymentItem("pay 11", "110", "description", null),
            PaymentItem("pay 12", "120", "description", null),
            PaymentItem("pay 13", "130", "description", null),
            PaymentItem("pay 14", "140", "description", null),
            PaymentItem("pay 15", "150", "description", null),
            PaymentItem("pay 16", "160", "description", null),
            PaymentItem("pay 17", "170", "description", null),
            PaymentItem("pay 18", "180", "description", null),
            PaymentItem("pay 19", "190", "description", null),
            PaymentItem("pay 20", "200", "description", null),
            PaymentItem("pay 21", "210", "description", null),
            PaymentItem("pay 22", "220", "description", null),
            PaymentItem("pay 23", "230", "description", null),
            PaymentItem("pay 24", "240", "description", null),
            PaymentItem("pay 25", "250", "description", null)
        )

        val adapter = PaymentsRvAdapter(list)
        val layoutManager = LinearLayoutManager(this)

        binding.rvPayments.adapter = adapter
        binding.rvPayments.layoutManager = layoutManager*/