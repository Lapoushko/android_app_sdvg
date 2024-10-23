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
 * @param priorityItem приоритет выполнения задачи
 * @param categoryItem категория задачи
 */
class Task(
    val name: String = "Нет названия",
    val description: String = "Нет описания",
    val timer: Long = 0L,
    val capacity: Long = 0L,
    val periodicity: Int = 0,
    val priority: Priority = Priority.HIGH,
    val category: Category = Category.STANDART
)