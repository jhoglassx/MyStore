package com.js.mystore

import android.app.Application
import com.js.mystore.di.daoModule
import com.js.mystore.di.repositoryModule
import com.js.mystore.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyStoreApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MyStoreApp)
            modules(repositoryModule, viewModelModule, daoModule)
        }
    }
}
