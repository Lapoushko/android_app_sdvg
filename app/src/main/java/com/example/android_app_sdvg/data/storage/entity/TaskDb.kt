package com.example.android_app_sdvg.data.storage.entity

import com.example.android_app_sdvg.domain.entity.category.Category
import com.example.android_app_sdvg.domain.entity.prioriry.Priority

/**
 * @author Lapoushko
 * Задачи db
 * @param id айди задачи
 * @param name имя задачи
 * @param description описание задачи
 * @param timer Длительность выполнения задачи
 * @param capacity объём задачи
 * @param periodicity периодичность появления задачи
 * @param priority приоритет выполнения задачи
 * @param category категория задачи
 */
data class TaskDb(
    val id: Long? = null,
    val name: String?,
    val description: String?,
    val dateStart: Long?,
    val timer: Long?,
    val capacity: Long?,
    val periodicity: Int?,
    val priority: Priority?,
    val category: Category?
)