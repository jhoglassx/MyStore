package com.js.mystore.di

import com.js.mystore.ui.buy.BuyViewModel
import com.js.mystore.ui.main.MainViewModel
import com.js.mystore.ui.main.ProductViewModel
import com.js.mystore.ui.sell.SellViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory {
        MainViewModel(get())
    }

    factory {
        ProductViewModel(get())
    }

    factory {
        BuyViewModel(get(), get(), get())
    }

    factory {
        SellViewModel(get(), get(), get())
    }
}
