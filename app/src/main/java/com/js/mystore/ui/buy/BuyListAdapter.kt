package com.js.mystore.ui.buy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.js.mystore.databinding.ListItemBinding
import com.js.mystore.model.ProductsList

class BuyListAdapter(private val buyList: List<ProductsList>) : RecyclerView.Adapter<BuyListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyListViewHolder {
        val itemView = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BuyListViewHolder, position: Int) {
        val buy = buyList[position]
        holder.bind(buy)
    }

    override fun getItemCount(): Int = buyList.size
}
