package com.abdosharaf.paymentstracker.ui.oldMain.paymentslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.paymentstracker.databinding.ItemPaymentBinding
import com.abdosharaf.paymentstracker.models.ExpenseItem

class PaymentsAdapter : ListAdapter<ExpenseItem, PaymentViewHolder>(PaymentsDiffCallback) {

    var onItemClicked: ((ExpenseItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        return PaymentViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }
}

class PaymentViewHolder private constructor(private val binding: ItemPaymentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(expenseItem: ExpenseItem, clickListener: ((ExpenseItem) -> Unit)?) {
        binding.item = expenseItem
        binding.root.setOnClickListener {
            clickListener?.invoke(expenseItem)
        }
    }

    companion object {
        fun from(parent: ViewGroup): PaymentViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return PaymentViewHolder(
                ItemPaymentBinding.inflate(layoutInflater, parent, false)
            )
        }
    }
}

object PaymentsDiffCallback : DiffUtil.ItemCallback<ExpenseItem>() {
    override fun areItemsTheSame(oldItem: ExpenseItem, newItem: ExpenseItem): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: ExpenseItem, newItem: ExpenseItem): Boolean {
        return newItem == oldItem
    }
}