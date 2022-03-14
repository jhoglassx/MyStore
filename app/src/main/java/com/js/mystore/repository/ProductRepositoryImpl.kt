package com.js.mystore.repository

import com.js.mystore.data.dao.StoreDao
import com.js.mystore.model.Product

class ProductRepositoryImpl(private val storeDao: StoreDao) : ProductRepository {
    override suspend fun getProduct(productId: Long): Product {
        return storeDao.getProduct(productId)
    }

    override suspend fun getProductAll(companyId: Long, status: Boolean): List<Product> {
        return storeDao.getProductAll(companyId, status)
    }

    override suspend fun setProduct(product: Product) {
        return storeDao.insertProduct(product)
    }

    override suspend fun updateProduct(product: Product) {
        return storeDao.updateProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        return storeDao.updateProduct(product)
    }
}
