package com.js.mystore.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.js.mystore.databinding.ListSellBinding
import com.js.mystore.model.relations.SellProducts

class MainSellListViewHolder(private val binding: ListSellBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(sell: SellProducts) = with(binding.root) {
        var prodQtd: Int = 0
        var prodValue: Double = 0.0
        sell.sells.forEach {
            prodQtd += it.amount
            prodValue += it.saleValue
        }

        binding.txtItemId.text = sell.sell.sellId.toString()
        binding.txtItemQtdValue.text = prodQtd.toString()
        binding.txtItemPriceValue.text = (prodQtd * prodValue).toString().replace('.', ',')
    }
}
