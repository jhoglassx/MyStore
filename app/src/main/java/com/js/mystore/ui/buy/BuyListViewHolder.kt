package com.js.mystore.ui.buy

import androidx.recyclerview.widget.RecyclerView
import com.js.mystore.databinding.ListItemBinding
import com.js.mystore.model.ProductsList

class BuyListViewHolder(private val binding: ListItemBinding, listener: BuyListAdapter.OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }

    fun bind(buy: ProductsList) = with(binding.root) {
        binding.txtItemId.text = buy.productId.toString()
        binding.txtItemName.text = buy.productName
        binding.txtItemQtdValue.text = buy.qtd.toString()
        binding.txtItemPriceValue.text = buy.price.toString()
    }
}
