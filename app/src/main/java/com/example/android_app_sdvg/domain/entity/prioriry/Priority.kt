package com.example.android_app_sdvg.domain.entity.prioriry

import com.example.android_app_sdvg.domain.entity.category.Category

/**
 * @author Lapoushko
 * Приоритет
 */
enum class Priority(val naming: String) {
    HIGH(naming = "Высокий"),
    MEDIUM(naming = "Средний"),
    LOW(naming = "Низкий")
}

/**
 * Экстеншен, позволяющий получать enum от его имени
 */
inline fun <reified T : Enum<T>> String.getEnum(default: T): T {
    return enumValues<T>().find { it.name == this } ?: default
}

