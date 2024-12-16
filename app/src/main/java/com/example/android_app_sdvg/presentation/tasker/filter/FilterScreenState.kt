package com.example.android_app_sdvg.presentation.tasker.filter

import com.example.android_app_sdvg.presentation.model.task.DatesItem

/**
 * @author Lapoushko
 */
interface FilterScreenState {
    val filtersPriority: MutableSet<String>
    val sortParam: String
    val selectedDates: DatesItem
    val showModal: Boolean
}