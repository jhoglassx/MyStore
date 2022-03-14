package com.js.mystore.repository

import com.js.mystore.model.ProductSell
import com.js.mystore.model.relations.ProductSells

interface ProductSellRepository {

    suspend fun getProductSell(productId: Long): ProductSell
    suspend fun setProductSell(productSell: ProductSell): Long
    suspend fun updateProductSell(productSell: ProductSell)
    suspend fun deleteProductSell(productSell: ProductSell)
}
