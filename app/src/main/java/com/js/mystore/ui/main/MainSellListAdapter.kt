package com.js.mystore.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.js.mystore.databinding.ListSellBinding
import com.js.mystore.model.relations.SellProducts

class MainSellListAdapter(private val sell: List<SellProducts>) : RecyclerView.Adapter<MainSellListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainSellListViewHolder {
        val itemView = ListSellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainSellListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainSellListViewHolder, position: Int) {
        var sell = sell.get(position)

        holder.bind(sell)
    }

    override fun getItemCount(): Int = sell.size
}
