package com.abdosharaf.paymentstracker.ui.oldMain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdosharaf.paymentstracker.base.BaseFragment
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import com.abdosharaf.paymentstracker.databinding.FragmentBalanceBinding
import com.abdosharaf.paymentstracker.utils.readFromPref
import kotlinx.coroutines.launch

class BalanceFragment : BaseFragment() {

    private lateinit var binding: FragmentBalanceBinding
    private lateinit var dataStore: DataStore<Preferences>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBalanceBinding.inflate(layoutInflater)

        dataStore = requireContext().createDataStore(name = "my_data_store")

        lifecycleScope.launch {
            val value = readFromPref(dataStore, "balance") as String?
            binding.balance = value ?: "No value!"
        }

        return binding.root
    }
}