package com.js.mystore.di

import com.js.mystore.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    factory<CompanyRepository> {
        CompanyRepositoryImpl(get())
    }
    factory<ProductRepository> {
        ProductRepositoryImpl(get())
    }

    factory<BuyRepository> {
        BuyRepositoryImpl(get())
    }

    factory<SellRepository> {
        SellRepositoryImpl(get())
    }

    factory<ProductSellRepository> {
        ProductSellRepositoryImpl(get())
    }

    factory<ProductBuyRepository> {
        ProductBuyRepositoryImpl(get())
    }
}
