package com.muniz.mbtest.domain.repositories

import com.muniz.mbtest.domain.model.Exchange
import com.muniz.mbtest.domain.model.ExchangeDetail
import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {

    suspend fun getExchanges(): Flow<List<Exchange>>

    suspend fun getExchangeById(exchangeId: String): Flow<ExchangeDetail>

}