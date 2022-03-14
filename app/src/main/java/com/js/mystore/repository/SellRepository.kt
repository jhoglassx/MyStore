package com.js.mystore.repository

import com.js.mystore.model.Sell
import com.js.mystore.model.relations.SellProducts

interface SellRepository {
    suspend fun getSell(sellId: Long): Sell
    suspend fun getSellAll(): List<Sell>
    suspend fun setSell(sell: Sell): Long
    suspend fun updateSell(sell: Sell)
    suspend fun deleteSell(sell: Sell)
    suspend fun getSellProductsAll(): List<SellProducts>
}
