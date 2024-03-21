package org.lotka.xenonx.domain.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun convertMillisToLocalizedDateTime(millis: Long?, timeZoneId: String): String {
    if (millis == null) return "N/A" // or any default string you prefer
    
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone(timeZoneId)
    return formatter.format(Date(millis))
}