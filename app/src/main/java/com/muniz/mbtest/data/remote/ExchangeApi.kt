package com.muniz.mbtest.data.remote

import com.muniz.mbtest.data.model.ExchangeResponse
import retrofit2.Call
import retrofit2.http.GET

interface ExchangeApi {

    @GET("exchanges")
    fun getExchanges(): Call<List<ExchangeResponse>>

    @GET("exchanges/{id}")
    fun getExchangeById(exchangeId: String): Call<ExchangeResponse>

}