package com.js.mystore.repository

import com.js.mystore.data.dao.StoreDao
import com.js.mystore.model.Buy

class BuyRepositoryImpl(private val storeDao: StoreDao) : BuyRepository {
    override suspend fun getBuy(buyId: Int): Buy {
        return storeDao.getBuy(buyId)
    }

    override suspend fun setBuy(buy: Buy): Long {
        return storeDao.insertBuy(buy)
    }

    override suspend fun updateBuy(buy: Buy) {
        storeDao.updateBuy(buy)
    }

    override suspend fun deleteBuy(buy: Buy) {
        storeDao.updateBuy(buy)
    }
}
