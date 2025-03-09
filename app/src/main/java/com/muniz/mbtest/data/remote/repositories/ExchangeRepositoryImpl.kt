package com.muniz.mbtest.data.remote.repositories

import awaitResponse
import com.muniz.mbtest.data.remote.ExchangeApi
import com.muniz.mbtest.domain.model.Exchange
import com.muniz.mbtest.domain.mapper.toExchange
import com.muniz.mbtest.domain.mapper.toExchangeDetail
import com.muniz.mbtest.domain.model.ExchangeDetail
import com.muniz.mbtest.domain.repositories.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExchangeRepositoryImpl(private val exchangeApi: ExchangeApi) : ExchangeRepository {

    override suspend fun getExchanges(): Flow<List<Exchange>> = flow {
        try {
            emit(
                exchangeApi.getExchanges()
                    .awaitResponse()
                    .filter { exchange -> !exchange.name.isNullOrEmpty() }
                    .map { response ->
                        response.toExchange()
                    })
        } catch (ex: Exception) {
            throw Exception("Erro ao buscar dados da API: ${ex.message}")
        }
    }

    override suspend fun getExchangeById(exchangeId: String): Flow<ExchangeDetail> = flow {
        try {
            emit(
                exchangeApi.getExchangeById(exchangeId)
                    .awaitResponse()
                    .firstNotNullOf { it.toExchangeDetail() }
            )
        } catch (ex: Exception) {
            throw Exception("Erro ao buscar dados da API: ${ex.message}")
        }
    }
}