package com.js.mystore.ui.sell

import androidx.recyclerview.widget.RecyclerView
import com.js.mystore.databinding.ListProductsSimpleBinding
import com.js.mystore.model.Product

class ProductListViewHolder(private val binding: ListProductsSimpleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) = with(binding.root) {
        binding.txtItemName.text = product.name
        binding.txtItemQtdValue.text = "10"
    }
}
