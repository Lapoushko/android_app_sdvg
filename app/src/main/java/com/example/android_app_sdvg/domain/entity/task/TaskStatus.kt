package com.example.android_app_sdvg.domain.entity.task

/**
 * @author Lapoushko
 */
enum class TaskStatus(
    val naming: String,
    val value: Int
){
    COMPLETED("Завершено", 2),
    MISSED("Пропущено", 1),
    IN_PROGRESS("В процессе", 0)
}

fun String.getTaskStatus() = TaskStatus.entries.find { this == it.naming }
