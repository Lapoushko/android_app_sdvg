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

fun String.toLongDate(): Long {
    val format = SimpleDateFormat(FORMAT, Locale.US)
    return format.parse(this)?.time ?: throw IllegalArgumentException("Invalid time string")
}

private const val FORMAT = "dd/MM/yyyy"