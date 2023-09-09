package com.abdosharaf.paymentstracker.ui.home.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdosharaf.paymentstracker.PrefKeys.BALANCE_KEY
import com.abdosharaf.paymentstracker.base.BaseFragment
import com.abdosharaf.paymentstracker.databinding.FragmentProfileBinding
import com.abdosharaf.paymentstracker.utils.removeFromPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        initMainClicks()

        return binding.root
    }

    private fun initMainClicks() {
        binding.btnResetBalance.setOnClickListener {
            removeFromPrefs(requireContext(), BALANCE_KEY)
        }
    }
}