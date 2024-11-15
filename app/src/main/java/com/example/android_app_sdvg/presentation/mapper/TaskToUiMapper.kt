package com.example.android_app_sdvg.presentation.mapper

import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.presentation.extension.toDateString
import com.example.android_app_sdvg.presentation.extension.toTimeString
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import javax.inject.Inject

/**
 * @author Lapoushko
 * перевод задачи domain в TaskUi
 */
interface TaskToUiMapper {
    operator fun invoke(task: Task): TaskItem
}

class TaskToUiMapperImpl @Inject constructor (): TaskToUiMapper{
    override fun invoke(task: Task): TaskItem {
        return TaskItem(
            name = task.name,
            description = task.description,
            dateStart = task.dateStart.toDateString(),
            dateEnd = task.dateEnd.toDateString(),
            timer = task.timer.toString(),
            capacity = task.capacity.toTimeString(),
            periodicity = task.periodicity.toString(),
            priorityItem = task.priority.naming,
            categoryItem = task.category.naming
        )
    }

}