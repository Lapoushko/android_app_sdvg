package com.example.android_app_sdvg.presentation.model.screen

import com.example.android_app_sdvg.presentation.model.profile.ProfileItem
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 * Скрины со своими данными
 */

sealed class ScreenItem{
    @Serializable
    data object CreateTask : ScreenItem()

    @Serializable
    data class EditTask(
        val task: TaskItem
    ) : ScreenItem()

    @Serializable
    data class EditProfile(
        val profile: ProfileItem
    ) : ScreenItem()
}