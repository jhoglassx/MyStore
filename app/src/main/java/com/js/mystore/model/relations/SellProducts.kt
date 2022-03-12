package com.js.mystore.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.js.mystore.model.Product
import com.js.mystore.model.ProductSell
import com.js.mystore.model.Sell

data class SellProducts(
    @Embedded val productSell: Sell,

    @Relation(
        parentColumn = "sellId",
        entityColumn = "productId",
        associateBy = Junction(ProductSell::class)
    )
    val products: List<Product>
)
