package com.js.mystore.ui.sell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.js.mystore.databinding.ListProductsSimpleBinding
import com.js.mystore.model.Product

class ProductListAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductListViewHolder>() {

    private lateinit var listener: OnItemClickListener

    private var selectedPosition = -1

    interface OnItemClickListener {
        fun onItemClick(product: Long, productName: String)
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val itemView = ListProductsSimpleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        var product = products.get(position)

        holder.bind(product)
        holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#F9F9F9"))

        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#DBDBDB"))
        } else {
            holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#F9F9F9"))
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(product.productId, product.name!!)
            selectedPosition = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = products.size
}
