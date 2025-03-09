package com.muniz.mbtest.data.model

import com.google.gson.annotations.SerializedName

data class ExchangeResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("volume_1day_usd") val volumeOneDayUsd: Double,
    @SerializedName("exchange_id") val exchangeId: String,
    @SerializedName("website") val website: String?,
    @SerializedName("data_start") val dataStart: String?,
    @SerializedName("data_end") val dataEnd: String?,
    @SerializedName("data_quote_start") val dataQuoteStart: String?,
    @SerializedName("data_orderbook_start") val dataOrderBookStart: String?,
    @SerializedName("data_trade_start") val dataTradeStart: String?,
    @SerializedName("data_trade_end") val dataTradeEnd: String?,
    @SerializedName("data_trade_count") val dataTradeCount: Int?,
    @SerializedName("data_symbols_count") val dataSymbolsCount: Int?,
    @SerializedName("volume_1hrs_usd") val volumeOneHrsUsd: Double?,
    @SerializedName("volume_1mth_usd") val volumeOneMthUsd: Double?,
    @SerializedName("metric_id") val metricId: List<String>?
)