package com.abdosharaf.paymentstracker.ui.paymentslist

/*import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.models.PaymentItem
import kotlinx.android.synthetic.main.payment_item.view.*

class PaymentsRvAdapter() : RecyclerView.Adapter<PaymentsRvAdapter.MyViewHolder>() {

    var onItemClicked : ((PaymentItem) -> Unit) ? = null

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view)

    private val differCallback = object : DiffUtil.ItemCallback<PaymentItem>() {
        override fun areItemsTheSame(oldItem: PaymentItem, newItem: PaymentItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PaymentItem, newItem: PaymentItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.payment_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = differ.currentList[position]
        if(item.desc.isNullOrEmpty()){
            holder.itemView.tvItemDescription.visibility = View.GONE
            holder.itemView.tvItemDescriptionLabel.visibility = View.GONE
        }else{
            holder.itemView.tvItemDescription.text = item.desc
        }
        holder.itemView.apply {
            tvItemName.text = item.name
            tvItemValue.text = item.value
        }

        holder.itemView.setOnClickListener {
            onItemClicked?.invoke(item)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}*/
