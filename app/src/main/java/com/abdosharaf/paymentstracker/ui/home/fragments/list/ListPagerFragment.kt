package com.abdosharaf.paymentstracker.ui.home.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.abdosharaf.paymentstracker.base.BaseFragment
import com.abdosharaf.paymentstracker.databinding.FragmentListPagerBinding
import com.abdosharaf.paymentstracker.models.ExpenseItem
import com.abdosharaf.paymentstracker.ui.oldMain.paymentslist.PaymentsAdapter
import com.abdosharaf.paymentstracker.ui.oldMain.paymentslist.PaymentsListViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "type"

@AndroidEntryPoint
class ListPagerFragment : BaseFragment() {

    private lateinit var binding: FragmentListPagerBinding
    private lateinit var expensesAdapter: PaymentsAdapter
    private lateinit var incomesAdapter: PaymentsAdapter
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
                binding.rvIncomes.isVisible = false
                setupExpensesRecycler()
            }

            false -> {
                binding.rvExpenses.isVisible = false
//                binding.btnExport.isVisible = false
                binding.rvIncomes.isVisible = true
                setupIncomesRecycler()
            }
        }

        /*binding.btnExport.setOnClickListener {
            exportPaymentsTable()
        }*/

        return binding.root
    }

    private fun setupExpensesRecycler() {
        expensesAdapter = PaymentsAdapter()
        expensesAdapter.onItemClicked = { _ ->
            showSuccessToast("Going to single expense screen....")
        }

        binding.rvExpenses.adapter = expensesAdapter
        viewModel.expensesList.observe(viewLifecycleOwner) { list ->
            expensesAdapter.submitList(list)
        }
    }

    private fun setupIncomesRecycler() {
        incomesAdapter = PaymentsAdapter()
        incomesAdapter.onItemClicked = { _ ->
            showSuccessToast("Going to single expense screen....")
        }

        binding.rvIncomes.adapter = incomesAdapter
        viewModel.incomesList.observe(viewLifecycleOwner) { list ->
            incomesAdapter.submitList(list.map { incomeItem ->
                ExpenseItem(
                    name = incomeItem.name,
                    value = incomeItem.value,
                    desc = incomeItem.desc,
                    date = incomeItem.date,
                    id = incomeItem.id
                )
            })
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