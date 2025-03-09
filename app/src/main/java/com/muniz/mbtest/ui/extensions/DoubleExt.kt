package com.muniz.mbtest.ui.extensions

fun Double.formatDecimalPlaces(places: Int = 5): Double {
    return "%.${places}f".format(this).toDouble()
}
