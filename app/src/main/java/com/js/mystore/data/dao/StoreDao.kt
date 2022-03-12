package com.js.mystore.data.dao

import androidx.room.*
import com.js.mystore.model.*
import com.js.mystore.model.relations.*

@Dao
interface StoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompany(company: Company)

    @Update
    suspend fun updateCompany(company: Company)

    @Transaction
    @Query("SELECT * FROM Company WHERE companyId = :companyId")
    suspend fun getCompany(companyId: Int): Company

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Query("SELECT * FROM Product WHERE productId = :productId")
    suspend fun getProduct(productId: Int): Product

    @Query("SELECT * FROM Product WHERE companyId =:companyId and status = :status")
    suspend fun getProductAll(companyId: Int, status: Boolean): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSale(productSell: Sell): Long

    @Update
    suspend fun updateSale(productSell: Sell)

    @Transaction
    @Query("SELECT * FROM Sell WHERE sellId = :saleId")
    suspend fun getSale(saleId: Int): Sell

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuy(buy: Buy): Long

    @Update
    suspend fun updateBuy(buy: Buy)

    @Transaction
    @Query("SELECT * FROM Buy WHERE buyId = :purchaseId")
    suspend fun getBuy(purchaseId: Int): Buy

    @Transaction
    @Query("SELECT * FROM Company WHERE companyId = :companyId")
    suspend fun getProductsOfCompany(companyId: Int): List<CompanyProducts>

    @Transaction
    @Query("SELECT * FROM Product WHERE productId = :productId")
    suspend fun getSalesOfProduct(productId: Int): List<ProductSells>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductSale(productSell: ProductSell): Long

    @Update
    suspend fun updateProductSale(productSell: ProductSell)

    @Transaction
    @Query("SELECT * FROM ProductSell WHERE productId = :productId")
    suspend fun getProductSale(productId: Int): ProductSell

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductPurchase(productBuy: ProductBuy)

    @Update
    suspend fun updateProductPurchase(productBuy: ProductBuy)

    @Transaction
    @Query("SELECT * FROM ProductBuy WHERE productId = :productId")
    suspend fun getProductPurchase(productId: Int): ProductBuy
}
