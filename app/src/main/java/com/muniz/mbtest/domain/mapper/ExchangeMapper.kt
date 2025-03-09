package com.muniz.mbtest.domain.mapper

import com.muniz.mbtest.data.model.ExchangeResponse
import com.muniz.mbtest.domain.model.Exchange
import com.muniz.mbtest.domain.model.ExchangeDetail

fun ExchangeResponse.toExchange() = Exchange(
    name = name.orEmpty(),
    exchangeId = exchangeId,
    volumeOneDayUsd = volumeOneDayUsd
)

fun ExchangeResponse.toExchangeDetail(): ExchangeDetail {
    return ExchangeDetail(
        name = this.name,
        volumeOneDayUsd = this.volumeOneDayUsd,
        exchangeId = this.exchangeId,
        website = this.website,
        dataStart = this.dataStart,
        dataEnd = this.dataEnd,
        dataQuoteStart = this.dataQuoteStart,
        dataOrderBookStart = this.dataOrderBookStart,
        dataTradeStart = this.dataTradeStart,
        dataTradeEnd = this.dataTradeEnd,
        dataTradeCount = this.dataTradeCount,
        dataSymbolsCount = this.dataSymbolsCount,
        volumeOneHrsUsd = this.volumeOneHrsUsd,
        volumeOneMthUsd = this.volumeOneMthUsd,
        metricId = this.metricId
    )
}