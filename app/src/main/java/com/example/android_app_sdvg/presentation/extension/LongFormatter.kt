package com.example.android_app_sdvg.presentation.extension

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * @author Lapoushko
 * перевод даты из Long в String
 * @return отформатированная дата
 */
fun Long.toDateString(): String {
    val dateTime = java.util.Date(this)
    val format = SimpleDateFormat(FORMAT, Locale.US)
    return format.format(dateTime)
}

fun Int.toTimeString(): String{
    val hour = this / 60
    val minute = this % 60
    return "${hour}${SEPARATOR_TIME}${minute}"
}

fun String.toLongDate(): Long {
    val format = SimpleDateFormat(FORMAT, Locale.US)
    return format.parse(this)?.time ?: throw IllegalArgumentException("Invalid time string")
}

fun String.toIntTime() : Int{
    val times = this.split(SEPARATOR_TIME)
    return times[0].toInt() * 60 + times[1].toInt()
}

private const val FORMAT = "dd/MM/yyyy"
private const val SEPARATOR_TIME = ":"