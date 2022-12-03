package com.abdosharaf.paymentstracker.ui

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.databinding.ActivityMainBinding
import com.abdosharaf.paymentstracker.db.PaymentDatabase
import com.abdosharaf.paymentstracker.models.PaymentItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDB : PaymentDatabase
    private lateinit var viewModel: SingleItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDB = PaymentDatabase.getDatabase(this)
        viewModel = ViewModelProvider(this)[SingleItemViewModel::class.java]

        val item = intent.getSerializableExtra("item") as PaymentItem

        binding.tvSingleItemName.text = item.name
        binding.tvSingleItemValue.text = item.value
        if(item.desc.isNullOrEmpty()){
            binding.tvSingleItemDescription.visibility = View.GONE
            binding.tvSingleItemDescriptionLabel.visibility = View.GONE
        } else{
            binding.tvSingleItemDescription.text = item.desc
        }

        val dialog1 = AlertDialog.Builder(this)
            .setTitle("Alert!!")
            .setMessage("Are you sure you want to delete this?")
            .setIcon(R.drawable.ic_baseline_pan_tool_24)
            .setPositiveButton("Yes"){ _, _ ->
                viewModel.deleteItem(true)
            }
            .setNegativeButton("No") { _, _ ->
                //Toast.makeText(this, "You didn't add him to the list", Toast.LENGTH_SHORT).show()
                viewModel.deleteItem(false)
            }
            .create()

        binding.btnDeleteItem.setOnClickListener {
            dialog1.show()
        }
        viewModel.flagDeleteItem.observe(this){
            if(it){
                lifecycleScope.launch(Dispatchers.IO) {
                    appDB.paymentDao().deletePayment(item)
                }
                Toast.makeText(this, "You deleted it successfully ♥", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}