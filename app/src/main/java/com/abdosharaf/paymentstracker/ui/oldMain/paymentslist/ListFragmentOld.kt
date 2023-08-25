package com.abdosharaf.paymentstracker.ui.oldMain.paymentslist

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.base.BaseFragment
import com.abdosharaf.paymentstracker.databinding.FragmentListOldBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragmentOld : BaseFragment() {

    private lateinit var binding: FragmentListOldBinding
    private val viewModel: PaymentsListViewModel by viewModels()
    private val adapter = PaymentsAdapter()
    private lateinit var dialog: AlertDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListOldBinding.inflate(inflater, container, false)

        binding.rvPayments.adapter = adapter
        viewModel.list.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            binding.rvPayments.isVisible = list.isNotEmpty()
            binding.emptyState.isVisible = list.isEmpty()
        }

        viewModel.itemsDeleted.observe(viewLifecycleOwner){
            if(it == true){
                showSuccessToast("You deleted all successfully ♥")
                viewModel.resetDelete()
            }
        }

//        initMainClicks()

        return binding.root
    }

    private fun initMainClicks() {
        adapter.onItemClicked = { item ->
            findNavController().navigate(
                com.abdosharaf.paymentstracker.ui.oldMain.paymentslist.ListFragmentOldDirections.actionListFragmentToSinglePaymentFragment(
                    item.id
                )
            )
        }

        binding.fbAddNewPayment.setOnClickListener {
            findNavController().navigate(com.abdosharaf.paymentstracker.ui.oldMain.paymentslist.ListFragmentOldDirections.actionListFragmentToAddNewFragment())
        }

        dialog = AlertDialog.Builder(requireContext())
            .setTitle("Alert!!")
            .setMessage("Are you sure you want to delete all payments?")
            .setIcon(R.drawable.ic_baseline_pan_tool_24)
            .setPositiveButton("Yes"){ _, _ ->
                viewModel.deleteAll()
            }
            .setNegativeButton("No") { _, _ ->
                // Nothing here
            }
            .create()

        binding.btnDeleteAll.setOnClickListener {
            lifecycleScope.launch {
                dialog.show()
            }
        }

        binding.btnShowBalance.setOnClickListener {
            findNavController().navigate(com.abdosharaf.paymentstracker.ui.oldMain.paymentslist.ListFragmentOldDirections.actionListFragmentToBalanceFragment())
        }
    }
}