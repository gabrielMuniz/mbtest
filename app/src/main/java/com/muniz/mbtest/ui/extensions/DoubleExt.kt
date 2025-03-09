package com.muniz.mbtest.ui.extensions

fun Double.formatDecimalPlaces(places: Int = 5): String {
    return "%.${places}f".format(this)
}
