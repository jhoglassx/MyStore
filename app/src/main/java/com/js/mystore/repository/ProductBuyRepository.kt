package com.js.mystore.repository

import com.js.mystore.model.ProductBuy

interface ProductBuyRepository {

    suspend fun getProductBuy(buyId: Int): ProductBuy
    suspend fun setProductBuy(buyBuy: ProductBuy)
    suspend fun updateProductBuy(productBuy: ProductBuy)
    suspend fun deleteProductBuy(productBuy: ProductBuy)
}