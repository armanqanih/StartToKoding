package org.lotka.xenonx.presentation.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

object TimeUtils {

    fun convertMillisToLocalizedDateTime(millis: Long, timeZoneId: String): String {
        val calendar = Calendar.getInstance().apply { timeInMillis = millis }
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone(timeZoneId)
        return simpleDateFormat.format(calendar.time)
    }

    fun minutesAgo(millis: Long): Long {
        val currentMillis = System.currentTimeMillis()
        val minutesElapsed = (currentMillis - millis) / (1000 * 60)
        return minutesElapsed
    }


    fun getTimePassed(utcTimestampSeconds: Long, timeZoneId: String): String {
        val minute = 60 * 1000
        val hour = 60 * minute
        val day = 24 * hour
        val month = 30L * day
        val year = 365L * day

        // Convert UTC to local time
        val utcTimestampMillis = utcTimestampSeconds * 1000
        val timeZone = TimeZone.getTimeZone(timeZoneId)
        val offset = timeZone.getOffset(utcTimestampMillis)
        val localTimestamp = utcTimestampMillis + offset

        // Calculate the elapsed time in local timezone
        var diff = System.currentTimeMillis() + TimeZone.getDefault()
            .getOffset(System.currentTimeMillis()) - localTimestamp

        val pastYears = diff / year
        diff %= year

        val pastMonths = diff / month
        diff %= month

        val pastDays = diff / day
        diff %= day

        val pastHours = diff / hour
        diff %= hour

        val pastMinutes = diff / minute

        return when {
            pastYears > 0 -> when {
                pastMonths > 0 -> "$pastYears سال و $pastMonths ماه پیش"
                else -> "$pastYears سال پیش"
            }

            pastMonths > 0 -> "$pastMonths ماه پیش"
            pastDays > 0 -> "$pastDays روز پیش"
            pastHours > 0 -> when {
                pastMinutes > 0 -> "$pastHours ساعت و $pastMinutes دقیقه پیش"
                else -> "$pastHours ساعت پیش"
            }

            else -> "$pastMinutes دقیقه پیش"
        }
    }


}