package com.example.android_app_sdvg.data.storage.mock

import com.example.android_app_sdvg.data.storage.entity.TaskDb
import com.example.android_app_sdvg.domain.entity.category.Category
import com.example.android_app_sdvg.domain.entity.prioriry.Priority

/**
 * @author Lapoushko
 */
class MockTasks {
    var tasks =
        mutableListOf(
            TaskDb(
                id = 1,
                name = "Название",
                description = "Описание",
                dateStart = 0L,
                timer = 0L,
                capacity = 0L,
                periodicity = 1,
                priority = Priority.HIGH,
                category = Category.STANDART
            )
        )
}