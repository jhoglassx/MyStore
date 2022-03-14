package com.js.mystore.ui.product

import androidx.recyclerview.widget.RecyclerView
import com.js.mystore.databinding.ListProductsBinding
import com.js.mystore.model.Product

class ProductViewHolder(private val binding: ListProductsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) = with(binding.root) {

        binding.txtItemName.text = product.name
        binding.txtDetails.text = product.description
    }
}
