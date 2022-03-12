package com.js.mystore.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.js.mystore.model.Product
import com.js.mystore.model.ProductSell
import com.js.mystore.model.Sell

data class ProductSells(
    @Embedded val product: Product,

    @Relation(
        parentColumn = "productId",
        entityColumn = "sellId",
        associateBy = Junction(ProductSell::class)
    )
    val productSells: List<Sell>
)
