package com.js.mystore.model

import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["productId", "sellId"])
data class ProductSell(
    val productId: Long,
    val sellId: Long,
    val amount: Int,
    val saleValue: Double,
    var status: Boolean = true,
    var updateAt: Date? = null,
    var createdAt: Date? = null,
)
