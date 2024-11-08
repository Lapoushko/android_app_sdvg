package com.example.android_app_sdvg.presentation.model.task

import android.os.Parcelable
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
    val name: String,
    val description: String,
    val dateStart: String,
    val timer: String,
    val capacity: String,
    val periodicity: String,
    val priorityItem: String,
    val categoryItem: String
) : Parcelable