package com.js.mystore.repository

import com.js.mystore.model.Product

interface ProductRepository {
    suspend fun getProduct(productId: Int): Product
    suspend fun getProductAll(companyId: Int, status: Boolean): List<Product>
    suspend fun setProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(product: Product)
}
