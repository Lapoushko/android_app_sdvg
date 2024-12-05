package com.example.android_app_sdvg.presentation.model.input

/**
 * @author Lapoushko
 */
class Input<T : CustomErrors>(
    var text: String,
    var error: T?
)

