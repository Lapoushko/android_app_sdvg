package com.example.android_app_sdvg.presentation.tasker

import com.example.android_app_sdvg.presentation.model.task.TaskItem

/**
 * @author Lapoushko
 */
interface TaskerScreenState {
    val showModal : Boolean
    val selectedDate: Long?
    val tasks: List<TaskItem>
}