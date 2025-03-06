package com.muniz.mbtest.data.remote

import com.muniz.mbtest.data.model.ExchangeResponse
import retrofit2.Response

interface ExchangeApi {

    fun getExchanges(): Response<List<ExchangeResponse>>

    fun getExchangeById(exchangeId: String): Response<ExchangeResponse>

}