package com.example.android_app_sdvg.presentation.model.screen

import com.example.android_app_sdvg.presentation.model.task.TaskItem
import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 */

sealed class ScreenItem{
    @Serializable
    data class CreateTask(
        val dateStart: Long
    ) : ScreenItem()
}