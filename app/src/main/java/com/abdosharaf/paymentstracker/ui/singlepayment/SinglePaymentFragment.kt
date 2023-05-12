package com.abdosharaf.paymentstracker.ui.singlepayment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdosharaf.paymentstracker.databinding.FragmentSinglePaymentBinding

class SinglePaymentFragment : Fragment() {

    private lateinit var binding: FragmentSinglePaymentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSinglePaymentBinding.inflate(inflater, container, false)

        return binding.root
    }
}