package com.abdosharaf.paymentstracker.ui.oldMain.singlepayment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.base.BaseFragment
import com.abdosharaf.paymentstracker.databinding.FragmentSinglePaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SinglePaymentFragment : BaseFragment() {

    private lateinit var binding: FragmentSinglePaymentBinding
    private val viewModel: SinglePaymentViewModel by viewModels()
    private val args: com.abdosharaf.paymentstracker.ui.oldMain.singlepayment.SinglePaymentFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getItem(args.id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSinglePaymentBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Alert!!")
            .setMessage("Are you sure you want to delete this?")
            .setIcon(R.drawable.ic_baseline_pan_tool_24)
            .setPositiveButton("Yes"){ _, _ ->
                viewModel.deleteItemFromDatabase()
            }
            .setNegativeButton("No") { _, _ -> }
            .create()

        viewModel.deleteItem.observe(viewLifecycleOwner) {
            if (it == true) {
                dialog.show()
            }
        }

        viewModel.deleteItemComplete.observe(viewLifecycleOwner) {
            if (it == true) {
                showSuccessToast("Item is deleted successfully!!")
                findNavController().navigateUp()
            }
        }

        return binding.root
    }
}