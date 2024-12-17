package com.example.android_app_sdvg.presentation.tasker

import com.example.android_app_sdvg.presentation.model.task.DatesItem
import com.example.android_app_sdvg.presentation.model.task.TaskItem

/**
 * @author Lapoushko
 */
interface TaskerScreenState {
    val tasks: List<TaskItem>
    val tasksWithDates: Map<String, List<TaskItem>>
    val isNeedToShowBottomSheet: Boolean
    val isNeedToShowCalendar: Boolean
    val selectedDates: DatesItem
}