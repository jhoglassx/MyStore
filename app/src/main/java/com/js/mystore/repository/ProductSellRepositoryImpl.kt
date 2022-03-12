package com.js.mystore.repository

import com.js.mystore.data.dao.StoreDao
import com.js.mystore.model.ProductSell

class ProductSellRepositoryImpl(private val storeDao: StoreDao) : ProductSellRepository {

    override suspend fun getProductSale(productId: Int): ProductSell {
        return storeDao.getProductSale(productId)
    }

    override suspend fun setProductSale(productSell: ProductSell): Long {
        return storeDao.insertProductSale(productSell)
    }

    override suspend fun updateProductSale(productSell: ProductSell) {
        return storeDao.updateProductSale(productSell)
    }

    override suspend fun deleteProductSale(productSell: ProductSell) {
        return storeDao.updateProductSale(productSell)
    }
}
