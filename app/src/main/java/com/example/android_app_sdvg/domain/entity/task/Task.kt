package com.example.android_app_sdvg.domain.entity.task

import com.example.android_app_sdvg.domain.entity.category.Category
import com.example.android_app_sdvg.domain.entity.prioriry.Priority

/**
 * Задача domain
 * @author Lapoushko
 * @param name имя задачи
 * @param description описание задачи
 * @param timer Длительность выполнения задачи
 * @param capacity объём задачи
 * @param periodicity периодичность появления задачи
 * @param priority приоритет выполнения задачи
 * @param category категория задачи
 */
class Task(
    val id: Long?,
    val name: String,
    val description: String,
    val dates: Dates,
    val timer: Long,
    val capacity: Int,
    val periodicity: Int,
    val priority: Priority,
    val category: Category,
    val taskStatus: TaskStatus
)