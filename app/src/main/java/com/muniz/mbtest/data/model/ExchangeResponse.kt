package com.muniz.mbtest.data.model

import com.google.gson.annotations.SerializedName

data class ExchangeResponse(
    val name: String,
    @SerializedName("exchange_id") val exchangeId: String,
    @SerializedName("volume_1day_usd") val volumeOneDayUsd: Double
)