package com.abdosharaf.paymentstracker.ui.oldMain.addnew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abdosharaf.paymentstracker.base.BaseFragment
import com.abdosharaf.paymentstracker.databinding.FragmentAddNewBinding
import com.abdosharaf.paymentstracker.models.ExpenseItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewFragment : BaseFragment() {


    private lateinit var binding: FragmentAddNewBinding
    private val viewModel: AddNewViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddNewBinding.inflate(inflater, container, false)

        // TODO: Check Form!!!!!!!!
        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val value = binding.etValue.text.toString()
            val description = binding.etDescription.text.toString()

            viewModel.addNewItem(ExpenseItem(name, value, description))

            findNavController().navigateUp()
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }
}

/*appDB = PaymentDatabase.getDatabase(this)
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
        }*/