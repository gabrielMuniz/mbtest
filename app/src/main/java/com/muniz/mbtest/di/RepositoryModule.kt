package com.muniz.mbtest.di

import com.muniz.mbtest.data.remote.repositories.ExchangeRepositoryImpl
import com.muniz.mbtest.domain.repositories.ExchangeRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { ExchangeRepositoryImpl(get()) as ExchangeRepository }

}