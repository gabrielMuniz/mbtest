package com.muniz.mbtest.ui.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import com.muniz.mbtest.BuildConfig
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

fun String.toFormattedDate(): String {
    return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
        toActualFormattedDate()
    else
        toLegacyFormattedDate()
}

@RequiresApi(Build.VERSION_CODES.O)
private fun String.toActualFormattedDate(): String {
    val instant = Instant.parse(this)

    val formatterBR = DateTimeFormatter
        .ofPattern("dd 'de' MMMM 'de' yyyy")
        .withLocale(Locale("pt", "BR"))
        .withZone(ZoneId.systemDefault())

    return formatterBR.format(instant)
}

private fun String.toLegacyFormattedDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    val date = inputFormat.parse(this) ?: return ""

    val outputFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("pt", "BR"))

    return outputFormat.format(date)
}
