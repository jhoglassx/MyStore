package com.js.mystore.repository

import com.js.mystore.data.dao.StoreDao
import com.js.mystore.model.ProductBuy

class ProductBuyRepositoryImpl(private val storeDao: StoreDao) : ProductBuyRepository {
    override suspend fun getProductBuy(buyId: Int): ProductBuy {
        return storeDao.getProductPurchase(buyId)
    }

    override suspend fun setProductBuy(buyBuy: ProductBuy) {
        return storeDao.insertProductPurchase(buyBuy)
    }

    override suspend fun updateProductBuy(productBuy: ProductBuy) {
        return storeDao.updateProductPurchase(productBuy)
    }

    override suspend fun deleteProductBuy(productBuy: ProductBuy) {
        return storeDao.updateProductPurchase(productBuy)
    }
}
