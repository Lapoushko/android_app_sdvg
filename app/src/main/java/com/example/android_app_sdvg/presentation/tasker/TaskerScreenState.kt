package com.example.android_app_sdvg.presentation.tasker

import com.example.android_app_sdvg.presentation.model.task.DatesItem
import com.example.android_app_sdvg.presentation.model.task.TaskItem

/**
 * @author Lapoushko
 */
interface TaskerScreenState {
    val showModal : Boolean
    val selectedDates: DatesItem?
    val tasks: List<TaskItem>
}