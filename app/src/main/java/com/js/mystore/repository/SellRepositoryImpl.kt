package com.js.mystore.repository

import com.js.mystore.data.dao.StoreDao
import com.js.mystore.model.Sell
import com.js.mystore.model.relations.ProductSells
import com.js.mystore.model.relations.SellProducts

class SellRepositoryImpl(private val storeDao: StoreDao) : SellRepository {
    override suspend fun getSell(sellId: Long): Sell {
        return storeDao.getSell(sellId)
    }

    override suspend fun getSellAll(): List<Sell> {
        return storeDao.getSellAll()
    }

    override suspend fun setSell(sell: Sell): Long {
        return storeDao.insertSale(sell)
    }

    override suspend fun updateSell(sell: Sell) {
        return storeDao.updateSale(sell)
    }

    override suspend fun deleteSell(sell: Sell) {
        return storeDao.updateSale(sell)
    }

    override suspend fun getSellProductsAll(): List<SellProducts> {
        return storeDao.getSellsOfProductAll()
    }
}
