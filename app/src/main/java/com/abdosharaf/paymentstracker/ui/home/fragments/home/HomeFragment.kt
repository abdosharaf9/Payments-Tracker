package com.abdosharaf.paymentstracker.ui.home.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.base.BaseFragment
import com.abdosharaf.paymentstracker.databinding.FragmentHomeBinding
import com.abdosharaf.paymentstracker.ui.addExpense.AddExpenseActivity
import com.abdosharaf.paymentstracker.ui.addIncome.AddIncomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initMainClicks()

        return binding.root
    }

    private fun initMainClicks() {
        binding.incomeLinear.setOnClickListener {
            startActivity(Intent(requireActivity(), AddIncomeActivity::class.java))
        }
        binding.expenseLinear.setOnClickListener {
            startActivity(Intent(requireActivity(), AddExpenseActivity::class.java))
        }
        binding.historyLinear.setOnClickListener {
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation).selectedItemId =
                R.id.listFragment
        }
        binding.ivUserImage.setOnClickListener {
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation).selectedItemId =
                R.id.profileFragment
        }

        binding.btnNotifications.setOnClickListener {
            // TODO: Go to notifications
            showSuccessToast("Going to your notifications...")
        }

        // TODO: Delete this dummy click
        binding.listPlaceholder.setOnClickListener {
            showSuccessToast("Wait for the list 👀!!")
        }

        // TODO: Delete these when complete the new ones
        binding.btnOldList.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToListFragmentOld())
        }
        binding.btnOldAdd.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNewFragment2())
        }
    }
}