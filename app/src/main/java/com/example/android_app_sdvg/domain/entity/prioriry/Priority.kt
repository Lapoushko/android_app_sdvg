package com.example.android_app_sdvg.domain.entity.prioriry

/**
 * @author Lapoushko
 * Приоритет
 */
enum class Priority(val naming: String) {
    HIGH(naming = "Высокий"),
    MEDIUM(naming = "Средний"),
    LOW(naming = "Низкий")
}

fun String.getPriority() = Priority.entries.find { it.naming == this }

