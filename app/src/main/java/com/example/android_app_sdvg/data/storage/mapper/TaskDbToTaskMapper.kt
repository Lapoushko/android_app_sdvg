package com.example.android_app_sdvg.data.storage.mapper

import com.example.android_app_sdvg.data.storage.entity.TaskDb
import com.example.android_app_sdvg.domain.entity.category.Category
import com.example.android_app_sdvg.domain.entity.prioriry.Priority
import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.presentation.model.task.TaskItem

/**
 * @author Lapoushko
 */
interface TaskDbToTaskMapper {
    suspend fun invoke(task: TaskDb): Task
}

class TaskDbToTaskMapperImpl(): TaskDbToTaskMapper{
    override suspend fun invoke(task: TaskDb): Task {
        return Task(
            name = task.name ?: "Нет названия",
            description = task.description ?: "Нет описания",
            timer = task.timer ?: 0L,
            dateStart = task.dateStart ?: 0L,
            capacity = task.capacity ?: 0L,
            periodicity = task.periodicity ?: 0,
            priority = task.priority ?: Priority.HIGH,
            category = task.category ?: Category.STANDART
        )
    }

}