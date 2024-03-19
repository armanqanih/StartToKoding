/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lotka.xenonx.presentation.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

/**
 * Returns the start of today in milliseconds
 */
fun getDefaultDateInMillis(): Long {
    val cal = Calendar.getInstance()
    val year = cal.get(Calendar.YEAR)
    val month = cal.get(Calendar.MONTH)
    val date = cal.get(Calendar.DATE)
    cal.clear()
    cal.set(year, month, date)
    return cal.timeInMillis
}

fun convertToMillis(dateTimeString: String): Long {
    val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
    utcFormat.timeZone = TimeZone.getTimeZone("UTC")

    // Parse the UTC datetime string
    val utcDate =
        utcFormat.parse(dateTimeString.substring(0, 23)) // Parse without the timezone offset

    // Convert UTC date to Tehran timezone
    val tehranTimeZone = TimeZone.getTimeZone("Asia/Tehran")
    val tehranCalendar = Calendar.getInstance(tehranTimeZone)
    tehranCalendar.time = utcDate

    // Return the time in milliseconds in Tehran's timezone
    return tehranCalendar.timeInMillis
}

fun timeAgoInPersian(timeInMillis: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timeInMillis

    val seconds = TimeUnit.MILLISECONDS.toSeconds(diff)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
    val hours = TimeUnit.MILLISECONDS.toHours(diff)
    val days = TimeUnit.MILLISECONDS.toDays(diff)
    val weeks = days / 7
    val months = days / 30
    val years = months / 12

    return when {
        seconds < 10 -> "لحظاتی پیش"
        seconds < 60 -> "$seconds ثانیه پیش"
        minutes < 2 -> "یک دقیقه پیش"
        minutes < 60 -> "$minutes دقیقه پیش"
        hours < 2 -> "یک ساعت پیش"
        hours < 12 -> "$hours ساعت پیش"
        days < 2 -> "یک روز پیش"
        weeks < 1 -> "$days روز پیش"
        weeks < 2 -> "یک هفته پیش"
        months < 1 -> "$weeks هفته پیش"
        months < 2 -> "یک ماه پیش"
        years < 1 -> {
            if (days > 30) {
                val remainingDays = days % 30
                if (remainingDays == 0L) "$months ماه پیش"
                else "$months ماه و $remainingDays روز پیش"
            } else "$days روز پیش"
        }

        years < 2 -> "یک سال پیش"
        else -> "$years سال پیش"
    }
}

