package com.example.android_app_sdvg.presentation.model.task

import android.os.Parcelable
import com.example.android_app_sdvg.domain.entity.category.CategoryItem
import com.example.android_app_sdvg.domain.entity.prioriry.PriorityItem
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * Задача view
 * @author Lapoushko
 * @param name имя задачи
 * @param description описание задачи
 * @param timer Длительность выполнения задачи
 * @param capacity объём задачи
 * @param periodicity периодичность появления задачи
 * @param priorityItem приоритет выполнения задачи
 * @param categoryItem категория задачи
 */
@Serializable
@Parcelize
data class TaskItem(
    val name: String = "Нет названия",
    val description: String = "Нет описания",
    val dateStart: Long = 0L,
    val timer: Long = 0L,
    val capacity: Long = 0L,
    val periodicity: Int = 0,
    val priorityItem: PriorityItem = PriorityItem.HIGH,
    val categoryItem: CategoryItem = CategoryItem.STANDART
) : Parcelable