package com.js.mystore.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.js.mystore.model.Product
import com.js.mystore.model.ProductBuy
import com.js.mystore.model.Buy

data class ProductBuys(
    @Embedded val product: Product,

    @Relation(
        parentColumn = "productId",
        entityColumn = "buyId",
        associateBy = Junction(ProductBuy::class)
    )
    val productBuys: List<Buy>
)
