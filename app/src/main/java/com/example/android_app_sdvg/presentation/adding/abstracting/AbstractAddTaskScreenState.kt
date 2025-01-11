package com.example.android_app_sdvg.presentation.adding.abstracting

import com.example.android_app_sdvg.presentation.model.input.TaskErrors
import com.example.android_app_sdvg.presentation.model.input.Input
import com.example.android_app_sdvg.presentation.model.task.DatesItem

/**
 * @author Lapoushko
 */
interface AbstractAddTaskScreenState {
    val name: Input<TaskErrors>
    val desc: Input<TaskErrors>
    val priority: Input<TaskErrors>
    val category: Input<TaskErrors>
    val periodicity: Input<TaskErrors>
    val showModal: Boolean
    val dateStart: Long?
    val dateEnd: Long?
    val dates: DatesItem
    val showTimePicker: Boolean
    val timePickerState: Pair<Int, Int>
    val capacity: String
    val errors: MutableSet<TaskErrors>
}