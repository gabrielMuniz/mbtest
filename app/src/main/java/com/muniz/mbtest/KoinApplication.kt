package com.muniz.mbtest

import android.app.Application
import com.muniz.mbtest.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class KoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinApplication)
            androidLogger()
            modules(appModules)
        }
    }
}