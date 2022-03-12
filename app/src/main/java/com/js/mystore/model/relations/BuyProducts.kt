package com.js.mystore.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.js.mystore.model.Product
import com.js.mystore.model.ProductBuy
import com.js.mystore.model.Buy

data class BuyProducts(
    @Embedded val productBuy: Buy,

    @Relation(
        parentColumn = "buyId",
        entityColumn = "productId",
        associateBy = Junction(ProductBuy::class)
    )
    val products: List<Product>
)
