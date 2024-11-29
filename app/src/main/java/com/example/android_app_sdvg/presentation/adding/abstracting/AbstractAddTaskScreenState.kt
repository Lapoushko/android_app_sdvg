package com.example.android_app_sdvg.presentation.adding.abstracting

import com.example.android_app_sdvg.presentation.model.task.DatesItem

/**
 * @author Lapoushko
 */
interface AbstractAddTaskScreenState {
    val name: String
    val desc: String
    val priority: String
    val category: String
    val periodicity: String
    val showModal: Boolean
    val dateStart: Long?
    val dateEnd: Long?
    val dates: DatesItem
    val showTimePicker: Boolean
    val timePickerState: Pair<Int, Int>
    val capacity: String
}