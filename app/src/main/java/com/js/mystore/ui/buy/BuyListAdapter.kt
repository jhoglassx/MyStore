package com.js.mystore.ui.buy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.js.mystore.databinding.ListItemBinding
import com.js.mystore.model.ProductsList

class BuyListAdapter(private val buy: List<ProductsList>) : RecyclerView.Adapter<BuyListViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    var selectedPosition = -1

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyListViewHolder {
        val itemView = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyListViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: BuyListViewHolder, position: Int) {
        holder.bind(buy[position])
        holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#F9F9F9"))

        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#222016"))
        } else {
            holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#F9F9F9"))
        }
    }

    override fun getItemCount(): Int = buy.size
}
