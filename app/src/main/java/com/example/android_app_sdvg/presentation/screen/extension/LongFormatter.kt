package com.example.android_app_sdvg.presentation.screen.extension

import java.text.DateFormat
import java.util.Locale

/**
 * @author Lapoushko
 */
fun Long.toDate(dateFormat: Int = DateFormat.MEDIUM): String {
    return DateFormat.getDateInstance(dateFormat, Locale.getDefault()).format(this)
}