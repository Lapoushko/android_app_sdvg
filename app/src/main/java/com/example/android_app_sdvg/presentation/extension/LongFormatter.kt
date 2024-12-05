package com.example.android_app_sdvg.presentation.extension

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale

/**
 * @author Lapoushko
 * перевод даты из Long в String
 * @return отформатированная дата
 */
fun Long.toDateString(): String {
    val dateTime = LocalDate.ofEpochDay(this / (24 * 60 * 60 * 1000)) // Преобразование в LocalDate
    val format = DateTimeFormatter.ofPattern(FORMAT, Locale.US)
    return dateTime.format(format)
}

fun Int.toTimeString(): String {
    val hour = this / 60
    val minute = this % 60
    return "${hour}${SEPARATOR_TIME}${minute}"
}

fun String.toLongDate(): Long {
    val format = DateTimeFormatter.ofPattern(FORMAT, Locale.US)
    return LocalDate.parse(this, format).toEpochDay() * (24 * 60 * 60 * 1000) // Возвращаем в миллисекундах
}

fun String.toIntTime(): Int {
    val times = this.split(SEPARATOR_TIME)
    return times[0].toInt() * 60 + times[1].toInt()
}

private const val FORMAT = "dd/MM/yyyy"
private const val SEPARATOR_TIME = ":"