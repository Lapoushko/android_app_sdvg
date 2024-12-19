package com.example.android_app_sdvg.presentation.extension

import android.net.Uri

/**
 * @author Lapoushko
 */
fun String.toFormattedUri(oldChar: Char = '/', newChar: Char = '\\'): Uri {
    return Uri.parse(this.replace(oldChar, newChar))
}

fun Uri.toFormattedString(oldChar: Char = '\\', newChar: Char = '/'): String {
    return this.toString().replace(oldChar, newChar)
}