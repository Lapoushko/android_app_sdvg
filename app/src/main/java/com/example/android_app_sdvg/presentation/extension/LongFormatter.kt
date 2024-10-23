package com.example.android_app_sdvg.presentation.extension

import java.text.DateFormat
import java.util.Locale

/**
 * @author Lapoushko
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val result = DateFormat
        .getDateInstance(dateFormat, Locale.getDefault())
        .format(this)

    return result
}