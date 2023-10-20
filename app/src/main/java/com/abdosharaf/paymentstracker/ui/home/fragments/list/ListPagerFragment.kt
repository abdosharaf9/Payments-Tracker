package com.abdosharaf.paymentstracker.ui.home.fragments.list

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.abdosharaf.paymentstracker.base.BaseFragment
import com.abdosharaf.paymentstracker.databinding.FragmentListPagerBinding
import com.abdosharaf.paymentstracker.ui.oldMain.paymentslist.PaymentsAdapter
import com.abdosharaf.paymentstracker.ui.oldMain.paymentslist.PaymentsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException

private const val ARG_PARAM1 = "type"

@AndroidEntryPoint
class ListPagerFragment : BaseFragment() {

    private lateinit var binding: FragmentListPagerBinding
    private lateinit var adapter: PaymentsAdapter
    private val viewModel: PaymentsListViewModel by viewModels()
    private var type: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getBoolean(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListPagerBinding.inflate(inflater, container, false)

        when (type) {
            true -> {
                binding.rvExpenses.isVisible = true
//                binding.btnExport.isVisible = true
                binding.income.isVisible = false
                setupRecycler()
            }

            false -> {
                binding.rvExpenses.isVisible = false
//                binding.btnExport.isVisible = false
                binding.income.isVisible = true
            }
        }

        /*binding.btnExport.setOnClickListener {
            exportPaymentsTable()
        }*/

        return binding.root
    }

    private fun setupRecycler() {
        adapter = PaymentsAdapter()
        adapter.onItemClicked = { _ ->
            showSuccessToast("Going to single expense screen....")
        }

        binding.rvExpenses.adapter = adapter
        viewModel.list.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    private fun exportPaymentsTable() {
        val directory = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val csvFile = File(directory, "payments.csv")

        try {
            csvFile.bufferedWriter().use { out ->
                out.write("ID, Name, Value, Description\n")
                for (record in viewModel.list.value!!) {
                    val line = "${record.print()}\n"
                    out.write(line)
                }
            }

            showSuccessToast("Done ✅")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(type: Boolean) =
            ListPagerFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_PARAM1, type)
                }
            }
    }
}