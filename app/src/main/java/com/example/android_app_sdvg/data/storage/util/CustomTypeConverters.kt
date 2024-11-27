package com.example.android_app_sdvg.data.storage.util

import androidx.room.TypeConverter
import com.example.android_app_sdvg.domain.entity.task.Dates

/**
 * @author Lapoushko
 * конвертер для dao
 */
class CustomTypeConverters {

    @TypeConverter
    fun convertListStringToString(list: List<String>?) : String? {
        return list?.joinToString(separator = SEPARATOR_KEY) { it }
    }

    @TypeConverter
    fun convertStringToListString(string: String?): List<String>? {
        return string?.split(SEPARATOR_KEY)
    }

    @TypeConverter
    fun convertDatesToString(dates: Dates?): String? {
        return dates?.let { "${it.dateStart}${SEPARATOR_KEY}${it.dateEnd}" }
    }

    @TypeConverter
    fun convertStringToDates(data: String?): Dates? {
        return data?.let {
            val parts = it.split(SEPARATOR_KEY)
            Dates(parts[0].toLong(), parts[1].toLong())
        }
    }

    companion object{
        private const val SEPARATOR_KEY = ", "
    }
}