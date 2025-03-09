package com.muniz.mbtest.domain.model

data class ExchangeDetail(
    val name: String?,
    val volumeOneDayUsd: Double,
    val exchangeId: String,
    val website: String?,
    val dataStart: String?,
    val dataEnd: String?,
    val dataQuoteStart: String?,
    val dataOrderBookStart: String?,
    val dataTradeStart: String?,
    val dataTradeEnd: String?,
    val dataTradeCount: Int?,
    val dataSymbolsCount: Int?,
    val volumeOneHrsUsd: Double?,
    val volumeOneMthUsd: Double?,
    val metricId: List<String>?
)