package com.js.mystore.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.js.mystore.model.Company
import com.js.mystore.model.Product

data class CompanyProducts(
    @Embedded val company: Company,

    @Relation(
        parentColumn = "companyId",
        entityColumn = "productId"
    )
    val products: List<Product>
)
