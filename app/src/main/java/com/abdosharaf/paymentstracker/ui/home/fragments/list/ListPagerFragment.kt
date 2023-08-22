package com.abdosharaf.paymentstracker.ui.home.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.databinding.FragmentListPagerBinding

private const val ARG_PARAM1 = "type"

class ListPagerFragment : Fragment() {

    private lateinit var binding: FragmentListPagerBinding
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
                binding.expenses.isVisible = true
                binding.income.isVisible = false
            }

            false -> {
                binding.expenses.isVisible = false
                binding.income.isVisible = true
            }
        }

        return binding.root
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