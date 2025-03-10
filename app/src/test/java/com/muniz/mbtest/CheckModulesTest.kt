package com.muniz.mbtest

import com.muniz.mbtest.di.networkModule
import com.muniz.mbtest.di.repositoryModule
import com.muniz.mbtest.di.viewModelModule
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify
import kotlin.test.Test

class CheckModulesTest : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkAllModules() {
        networkModule.verify()
        repositoryModule.verify()
        viewModelModule.verify()
    }
}
