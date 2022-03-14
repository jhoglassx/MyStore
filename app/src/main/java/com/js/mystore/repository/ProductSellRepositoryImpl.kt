package com.js.mystore.repository

import com.js.mystore.data.dao.StoreDao
import com.js.mystore.model.ProductSell
import com.js.mystore.model.relations.ProductSells

class ProductSellRepositoryImpl(private val storeDao: StoreDao) : ProductSellRepository {

    override suspend fun getProductSell(productId: Long): ProductSell {
        return storeDao.getProductSale(productId)
    }

    override suspend fun setProductSell(productSell: ProductSell): Long {
        return storeDao.insertProductSale(productSell)
    }

    override suspend fun updateProductSell(productSell: ProductSell) {
        return storeDao.updateProductSale(productSell)
    }

    override suspend fun deleteProductSell(productSell: ProductSell) {
        return storeDao.updateProductSale(productSell)
    }

}
