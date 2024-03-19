package org.lotka.xenonx.presentation.extension

import android.annotation.SuppressLint
import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.text.SimpleDateFormat
import java.util.*


val persianDateFormatWithTime = PersianDateFormat("H:i:s : Y/m/d")
val persianDateFormatWithoutTime = PersianDateFormat("Y/m/d")

fun Long.toPersianDate(): String {
    return persianDateFormatWithTime.format(PersianDate(this)).toString()
}


//supporting input format: "1401/11/11 : 11:26:25"
fun Long.toPersianDateWithoutTime(): String {
    return persianDateFormatWithoutTime.format(PersianDate(this)).toString()
}

@SuppressLint("SimpleDateFormat")
fun Long.toUTC(): String {

    val currentTimeZone = TimeZone.getDefault().id

    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    formatter.timeZone = TimeZone.getTimeZone(currentTimeZone)
    val output = formatter.format(Date(this))

    return output
}

fun PersianPickerDate.toPersianDate() = this.persianLongDate.toString()

//supporting format: 1970-01-01T00:00:00.000Z or 1970-01-01T00:00:00.000+00:00
@SuppressLint("SimpleDateFormat")
fun String.ISOtoPersianDate(): String {
    val date = this.substring(0, this.indexOf("T")) //remove hour,minute and second
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(this)?.time?.toPersianDateWithoutTime()
        ?: "-"
}