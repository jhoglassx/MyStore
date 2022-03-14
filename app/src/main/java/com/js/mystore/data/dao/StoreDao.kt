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
    suspend fun getCompany(companyId: Long): Company

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Query("SELECT * FROM Product WHERE productId = :productId")
    suspend fun getProduct(productId: Long): Product

    @Query("SELECT * FROM Product WHERE companyId =:companyId and status = :status")
    suspend fun getProductAll(companyId: Long, status: Boolean): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSale(productSell: Sell): Long

    @Update
    suspend fun updateSale(productSell: Sell)

    @Transaction
    @Query("SELECT * FROM Sell WHERE sellId = :sellId")
    suspend fun getSell(sellId: Long): Sell

    @Transaction
    @Query("SELECT * FROM Sell ")
    suspend fun getSellAll(): List<Sell>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuy(buy: Buy): Long

    @Update
    suspend fun updateBuy(buy: Buy)

    @Transaction
    @Query("SELECT * FROM Buy WHERE buyId = :buyId")
    suspend fun getBuy(buyId: Long): Buy

    @Transaction
    @Query("SELECT * FROM Buy ")
    suspend fun getBuyAll(): List<Buy>

    @Transaction
    @Query("SELECT * FROM Company WHERE companyId = :companyId")
    suspend fun getProductsOfCompany(companyId: Long): List<CompanyProducts>

    @Transaction
    @Query("SELECT * FROM Product WHERE productId = :productId")
    suspend fun getSellsOfProduct(productId: Int): List<ProductSells>

    @Transaction
    @Query("SELECT * FROM Sell")
    suspend fun getSellsOfProductAll(): List<SellProducts>

    @Transaction
    @Query("SELECT * FROM Product")
    suspend fun getProductOfSellsAll(): List<ProductSells>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductSale(productSell: ProductSell): Long

    @Update
    suspend fun updateProductSale(productSell: ProductSell)

    @Transaction
    @Query("SELECT * FROM ProductSell WHERE productId = :productId")
    suspend fun getProductSale(productId: Long): ProductSell

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductPurchase(productBuy: ProductBuy)

    @Update
    suspend fun updateProductPurchase(productBuy: ProductBuy)

    @Transaction
    @Query("SELECT * FROM ProductBuy WHERE productId = :productId")
    suspend fun getProductPurchase(productId: Long): ProductBuy
}
