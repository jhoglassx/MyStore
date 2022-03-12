package com.js.mystore.repository

import com.js.mystore.model.ProductSell

interface ProductSellRepository {

    suspend fun getProductSale(productId: Int): ProductSell
    suspend fun setProductSale(productSell: ProductSell): Long
    suspend fun updateProductSale(productSell: ProductSell)
    suspend fun deleteProductSale(productSell: ProductSell)
}
