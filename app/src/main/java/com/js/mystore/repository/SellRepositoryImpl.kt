package com.js.mystore.repository

import com.js.mystore.data.dao.StoreDao
import com.js.mystore.model.Sell

class SellRepositoryImpl(private val storeDao: StoreDao) : SellRepository {
    override suspend fun getSale(saleId: Int): Sell {
        return storeDao.getSale(saleId)
    }

    override suspend fun setSale(sell: Sell): Long {
        return storeDao.insertSale(sell)
    }

    override suspend fun updateSale(sell: Sell) {
        return storeDao.updateSale(sell)
    }

    override suspend fun deleteSale(sell: Sell) {
        return storeDao.updateSale(sell)
    }
}
