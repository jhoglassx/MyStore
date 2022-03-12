package com.js.mystore.model

import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["productId", "buyId"])
data class ProductBuy(
    val productId: Long,
    val buyId: Long,
    val amount: Int,
    val purchaseValue: Double,
    var status: Boolean = true,
    var updateAt: Date? = null,
    var createdAt: Date? = null,
)

