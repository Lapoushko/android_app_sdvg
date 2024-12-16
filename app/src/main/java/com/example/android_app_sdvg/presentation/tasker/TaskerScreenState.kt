package com.example.android_app_sdvg.presentation.tasker

import com.example.android_app_sdvg.presentation.model.task.TaskItem

/**
 * @author Lapoushko
 */
interface TaskerScreenState {
    val tasks: List<TaskItem>
}