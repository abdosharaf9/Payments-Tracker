package com.abdosharaf.paymentstracker.ui.home.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.base.BaseFragment
import com.abdosharaf.paymentstracker.databinding.FragmentListBinding
import com.abdosharaf.paymentstracker.ui.oldMain.paymentslist.PaymentsAdapter
import com.abdosharaf.paymentstracker.ui.oldMain.paymentslist.PaymentsListViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: PaymentsListViewModel by viewModels()
    private val expensesAdapter = PaymentsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        // TODO: Remove this old stuff!!
        /*binding.rvExpanses.apply {
            adapter = expensesAdapter
            setHasFixedSize(false)
        }

        viewModel.list.observe(viewLifecycleOwner) { list ->
            expensesAdapter.submitList(list)
        }*/

        setUpViews()

        return binding.root
    }

    private fun setUpViews() {
        val tabTitle =
            arrayOf(
                getString(R.string.expenses),
                getString(R.string.incomes)
            )
        binding.viewPager.adapter =
            ListViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.setCurrentItem(0, true)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
}