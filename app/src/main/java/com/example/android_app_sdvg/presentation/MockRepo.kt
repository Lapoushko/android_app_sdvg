package com.example.android_app_sdvg.presentation

import com.example.android_app_sdvg.presentation.model.task.TaskItem

/**
 * @author Lapoushko
 */
class MockRepo {
    var tasks =
        mutableListOf(
            TaskItem(), TaskItem(name = "dsds")
        )
}