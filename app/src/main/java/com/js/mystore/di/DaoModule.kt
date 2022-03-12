package com.js.mystore.di

import com.js.mystore.data.StoreDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val daoModule = module {
    single { StoreDataBase.getInstance(androidContext()).storeDao }
}