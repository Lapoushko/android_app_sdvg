package com.example.android_app_sdvg.presentation.extension

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale

/**
 * @author Lapoushko
 * перевод даты из Long в String
 * @return отформатированная дата
 */
fun Long.toDateString(timeDay: TimeDay = TimeDay.START, isRu: Boolean = true): String {
    val dateTime = LocalDate.ofEpochDay(this / (24 * 60 * 60 * 1000)) // Преобразование в LocalDate
    val format = if (isRu) RU_FORMATTER else US_FORMATTER
    return when (timeDay) {
        TimeDay.START -> {
            dateTime.atStartOfDay().format(format)
        }
        TimeDay.END -> {
            dateTime.atTime(23, 59, 59).format(format)
        }
        else -> {
            dateTime.format(format)
        }
    }
}

fun Int.toTimeString(): String {
    val hour = this / 60
    val minute = this % 60
    return "${hour}${SEPARATOR_TIME}${minute}"
}

fun String.toLongDate(isRu: Boolean = true): Long {
    val format = if (isRu) RU_FORMATTER else US_FORMATTER
    return LocalDate.parse(this, format).toEpochDay() * (24 * 60 * 60 * 1000) // Возвращаем в миллисекундах
}

fun String.toIntTime(): Int {
    val times = this.split(SEPARATOR_TIME)
    return times[0].toInt() * 60 + times[1].toInt()
}

private const val SEPARATOR_TIME = ":"
private val RU_FORMATTER = DateTimeFormatter.ofPattern("EEEE, dd MMMM", Locale("ru", "RU"))
private val US_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.US)

enum class TimeDay(){
    START,
    NOW,
    END
}