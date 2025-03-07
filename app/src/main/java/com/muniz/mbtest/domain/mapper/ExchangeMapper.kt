package com.muniz.mbtest.domain.mapper

import com.muniz.mbtest.data.model.ExchangeResponse
import com.muniz.mbtest.domain.Exchange

fun ExchangeResponse.toExchange() = Exchange(
    name = name,
    exchangeId = exchangeId,
    volumeOneDayUsd = volumeOneDayUsd
)