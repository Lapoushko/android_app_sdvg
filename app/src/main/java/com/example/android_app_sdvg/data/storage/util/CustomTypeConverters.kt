package com.example.android_app_sdvg.data.storage.util

import androidx.room.TypeConverter

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

    companion object{
        private const val SEPARATOR_KEY = ", "
    }
}