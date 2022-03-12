package com.js.mystore.ui.sell

import androidx.recyclerview.widget.RecyclerView
import com.js.mystore.databinding.ListItemBinding
import com.js.mystore.model.ProductsList

class SellListViewHolder(private val binding: ListItemBinding, listener: SellListAdapter.OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }

    fun bind(sell: ProductsList) = with(binding.root) {
        binding.txtItemId.text = sell.productId.toString()
        binding.txtItemName.text = sell.productName.toString()
        binding.txtItemQtdValue.text = sell.qtd.toString()
        binding.txtItemPriceValue.text = sell.price.toString()
    }
}
