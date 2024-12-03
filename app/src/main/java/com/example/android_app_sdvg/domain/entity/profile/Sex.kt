package com.example.android_app_sdvg.domain.entity.profile

import com.example.android_app_sdvg.domain.entity.prioriry.Priority
import com.example.android_app_sdvg.domain.entity.prioriry.getPriority

/**
 * @author Lapoushko
 */
enum class Sex(val naming: String) {
    MAN(naming = "Мужской"),
    WOMAN(naming = "Женский"),
    OTHER(naming = "Не указан")
}

fun String.getSex() = Sex.entries.find { it.naming == this } ?: Sex.OTHER