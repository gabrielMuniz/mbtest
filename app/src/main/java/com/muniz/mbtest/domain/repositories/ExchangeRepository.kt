package com.muniz.mbtest.domain.repositories

import com.muniz.mbtest.domain.Exchange
import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {

    suspend fun getExchanges(): Flow<List<Exchange>>

    suspend fun getExchangeById(exchangeId: String): Flow<Exchange>

}