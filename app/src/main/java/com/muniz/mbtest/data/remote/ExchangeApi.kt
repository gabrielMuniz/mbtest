package com.muniz.mbtest.data.remote

import com.muniz.mbtest.data.model.ExchangeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeApi {

    @GET("exchanges")
    fun getExchanges(): Call<List<ExchangeResponse>>

    @GET("exchanges/{filter_exchange_id}")
    fun getExchangeById(
        @Path("filter_exchange_id") exchangeId: String
    ): Call<List<ExchangeResponse>>

}