package com.js.mystore.ui.sell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.js.mystore.databinding.ListItemBinding
import com.js.mystore.model.ProductsList

class SellListAdapter(private val sellList: List<ProductsList>) : RecyclerView.Adapter<SellListViewHolder>() {

    private lateinit var listener: OnItemClickListener

    private var selectedPosition = -1

    interface OnItemClickListener {
        fun onItemClick(product: Long, productName: String)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellListViewHolder {
        val itemView = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SellListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SellListViewHolder, position: Int) {

        val sell = sellList[position]
        holder.bind(sell)
        holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#F9F9F9"))

        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("##FFFFFF"))
        } else {
            holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#F9F9F9"))
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(sell.productId, sell.productName)
            selectedPosition = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = sellList.size
}
