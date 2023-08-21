package com.abdosharaf.paymentstracker.ui.home.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.abdosharaf.paymentstracker.base.BaseFragment
import com.abdosharaf.paymentstracker.databinding.FragmentHomeBinding

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
        binding.btnOldList.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToListFragmentOld())
        }
        binding.btnOldAdd.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNewFragment2())
        }
    }
}