package com.muniz.mbtest

import android.app.Application
import com.muniz.mbtest.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MercadoBitCoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MercadoBitCoinApp)
            modules(networkModule)
        }
    }
}