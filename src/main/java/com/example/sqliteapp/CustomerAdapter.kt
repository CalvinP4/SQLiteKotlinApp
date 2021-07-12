package com.example.sqliteapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomerAdapter(private val mCustomers: List<CustomerModel>,
                      private val customerClickListener: CustomerClickListener):
    RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val customerDetails: TextView = itemView.findViewById(R.id.customer_details)

        fun bind(){
            customerDetails.setTextColor(Color.WHITE)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val customer = inflater.inflate(R.layout.customer_row, parent, false)
        return ViewHolder(customer)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val customer = mCustomers[position]
        val textView = holder.customerDetails
        textView.text = customer.toString()

        holder.itemView.setOnClickListener {
            customerClickListener.onCustomerClickListener(customer)
        }
    }

    override fun getItemCount(): Int {
        return mCustomers.size
    }
}