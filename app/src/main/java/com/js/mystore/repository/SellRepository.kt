package com.js.mystore.repository

import com.js.mystore.model.Sell

interface SellRepository {
    suspend fun getSale(saleId: Int): Sell
    suspend fun setSale(sell: Sell): Long
    suspend fun updateSale(sell: Sell)
    suspend fun deleteSale(sell: Sell)
}
