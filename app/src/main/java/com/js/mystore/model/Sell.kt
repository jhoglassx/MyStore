package com.js.mystore.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Sell(
    @PrimaryKey(autoGenerate = true)
    var sellId: Long? = 0,
    var valueSale: Double? = null,
    var status: Boolean = true,
    var updateAt: Date? = null,
    var createdAt: Date? = null,
)
