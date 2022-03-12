package com.js.mystore.repository

import com.js.mystore.model.Buy

interface BuyRepository {
    suspend fun getBuy(buyId: Int): Buy
    suspend fun setBuy(buy: Buy): Long
    suspend fun updateBuy(buy: Buy)
    suspend fun deleteBuy(buy: Buy)
}
