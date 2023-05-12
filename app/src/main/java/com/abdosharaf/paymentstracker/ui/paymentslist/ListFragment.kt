package com.abdosharaf.paymentstracker.ui.paymentslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addNewFragment)
        }

        return binding.root
    }
}