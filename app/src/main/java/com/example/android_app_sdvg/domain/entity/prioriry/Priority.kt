package com.example.android_app_sdvg.domain.entity.prioriry

/**
 * @author Lapoushko
 * Приоритет
 */
enum class Priority(name: String) {
    HIGH(name = "Высокий"),
    MEDIUM(name = "Средний"),
    LOW(name = "Низкий")
}

/**
 * Экстеншен, позволяющий получать enum от его имени
 */
inline fun <reified T : Enum<T>> String.getEnum(): T? {
    return enumValueOf<T>(this)
        .let { T::class.java.enumConstants?.find { enum -> enum.name == this } }
}