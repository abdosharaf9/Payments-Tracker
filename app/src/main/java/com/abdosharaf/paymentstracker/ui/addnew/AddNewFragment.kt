package com.abdosharaf.paymentstracker.ui.addnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdosharaf.paymentstracker.databinding.FragmentAddNewBinding

class AddNewFragment : Fragment() {


    private lateinit var binding: FragmentAddNewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddNewBinding.inflate(inflater, container, false)

        return binding.root
    }
}