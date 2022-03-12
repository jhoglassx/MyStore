package com.js.mystore.ui.sell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.js.mystore.databinding.ListItemBinding
import com.js.mystore.model.ProductsList

class SellListAdapter(private val sales: List<ProductsList>) : RecyclerView.Adapter<SellListViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    var selectedPosition = -1

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellListViewHolder {
        val itemView = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SellListViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: SellListViewHolder, position: Int) {
        holder.bind(sales[position])
        holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#F9F9F9"))

        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#222016"))
        } else {
            holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#F9F9F9"))
        }
    }

    override fun getItemCount(): Int = sales.size
}
