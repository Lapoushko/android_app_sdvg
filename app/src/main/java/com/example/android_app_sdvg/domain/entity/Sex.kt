package com.example.android_app_sdvg.domain.entity

/**
 * @author Lapoushko
 * Перечисление полов
 */
enum class Sex(val sexName: String) {
    MAN("Мужской"),
    WOMAN("Женский"),
    OTHER("Другой")
}

/**
 * Получить enum имени пола
 * @param sexName имя пола
 * @return enum пола
 */
fun String.getSex(sexName: String) : Sex =
    Sex.entries.find { it.sexName == sexName } ?: Sex.OTHER