package com.muniz.mbtest.data.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-CoinAPI-Key", apiKey)
            .build()
        return chain.proceed(request)
    }
}
