package com.example.android_app_sdvg.presentation.model.screen

import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 * Скрины со своими данными
 */

sealed class ScreenItem{
    @Serializable
    data class CreateTask(
        val dateStart: Long
    ) : ScreenItem()
}