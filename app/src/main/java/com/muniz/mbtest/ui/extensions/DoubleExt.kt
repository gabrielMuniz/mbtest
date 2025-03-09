package com.muniz.mbtest.ui.extensions

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.formatDecimalPlaces(): String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.FLOOR
    return df.format(this).toDouble().toString()
}