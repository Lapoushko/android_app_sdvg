package com.example.android_app_sdvg.domain.entity.prioriry

/**
 * @author Lapoushko
 * Приоритет
 */
enum class Priority(val naming: String, val value: Int) {
    HIGH(naming = "Высокий", 2),
    MEDIUM(naming = "Средний", 1),
    LOW(naming = "Низкий", 0)
}

fun String.getPriority() = Priority.entries.find { it.naming == this }

