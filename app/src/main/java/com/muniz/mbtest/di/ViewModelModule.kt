package com.muniz.mbtest.di

import com.muniz.mbtest.ui.ExchangeDetailViewModel
import com.muniz.mbtest.ui.ExchangeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ExchangeListViewModel(get()) }
    viewModel { ExchangeDetailViewModel(get()) }

}