package com.muniz.mbtest.di

import com.muniz.mbtest.data.network.RetrofitClient
import com.muniz.mbtest.data.remote.ExchangeApi
import org.koin.dsl.module

val networkModule = module {

    single { RetrofitClient.createService(ExchangeApi::class.java) }

}