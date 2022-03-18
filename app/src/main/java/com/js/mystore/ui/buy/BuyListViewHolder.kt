package com.js.mystore.ui.buy

import androidx.recyclerview.widget.RecyclerView
import com.js.mystore.databinding.ListItemBinding
import com.js.mystore.model.ProductsList

class BuyListViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(buy: ProductsList) = with(binding.root) {
        binding.txtItemId.text = buy.productId.toString()
        binding.txtItemName.text = buy.productName
        binding.txtItemQtdValue.text = buy.qtd.toString()
        binding.txtItemPriceValue.text = buy.price.toString().replace('.', ',')
    }
}
