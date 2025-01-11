package com.example.android_app_sdvg.presentation.model.test

/**
 * @author Lapoushko
 */
enum class StatusTest(val naming: String) {
    COMPLETED("Выполнен"),
    NOT_COMPLETED("Не выполнен")
}

fun String.getStatusTest() = StatusTest.entries.find { it.naming == this }