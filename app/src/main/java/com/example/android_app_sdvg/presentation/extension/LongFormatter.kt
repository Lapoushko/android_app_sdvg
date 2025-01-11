package com.example.android_app_sdvg.presentation.extension

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

/**
 * @author Lapoushko
 * перевод даты из Long в String
 * @return отформатированная дата
 */
fun Long.toDateString(timeDay: TimeDay = TimeDay.START, countryDateFormat: CountryDateFormat = CountryDateFormat.RU): String {
    val instant = Instant.ofEpochMilli(this)
    val zdt = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())

    val date = when (timeDay) {
        TimeDay.START -> zdt.with(LocalTime.MIN)
        TimeDay.END -> zdt.with(LocalTime.MAX)
    }

    return date.format(countryDateFormat.format)
}

fun Int.toTimeString(): String {
    val hour = this / 60
    val minute = this % 60
    return "${hour}${SEPARATOR_TIME}${minute}"
}

fun String.toLongDate(countryDateFormat: CountryDateFormat = CountryDateFormat.RU): Long {
    return LocalDate.parse(this, countryDateFormat.format).toEpochDay() * (24 * 60 * 60 * 1000) // Возвращаем в миллисекундах
}

fun String.toIntTime(): Int {
    val times = this.split(SEPARATOR_TIME)
    return times[0].toInt() * 60 + times[1].toInt()
}

fun Long.toNeedTime(timeDay: TimeDay = TimeDay.START): Long {
    val instant = Instant.ofEpochMilli(this)
    val calendar = Calendar.getInstance(TimeZone.getDefault())
    calendar.timeInMillis = this

    val deviceTimeZone = TimeZone.getDefault()
    val zoneId = ZoneId.of(deviceTimeZone.id)

    val zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId)

    val localDate = zonedDateTime.toLocalDate()

    val day: ZonedDateTime = when (timeDay) {
        TimeDay.START -> localDate.atStartOfDay().atZone(zoneId)
        TimeDay.END -> localDate.atTime(23, 59, 59).atZone(zoneId)
    }

    return day.toInstant().toEpochMilli()
}

fun getCurrentDay(timeDay: TimeDay = TimeDay.START) : Long{
    val today = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    return today.toNeedTime(timeDay)
}

private const val SEPARATOR_TIME = ":"

enum class TimeDay{
    START,
    END
}

enum class CountryDateFormat(val format: DateTimeFormatter){
    RU(DateTimeFormatter.ofPattern("EEEE, dd MMMM", Locale("ru", "RU"))),
    US(DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.US))
}