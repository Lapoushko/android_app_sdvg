package com.example.android_app_sdvg.presentation.mapper.task

import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.domain.entity.task.toDateItem
import com.example.android_app_sdvg.presentation.extension.toTimeString
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import javax.inject.Inject

/**
 * @author Lapoushko
 * перевод задачи domain в TaskUi
 */
interface TaskDomainToUiMapper {
    operator fun invoke(task: Task): TaskItem
}

class TaskDomainToUiMapperImpl @Inject constructor (): TaskDomainToUiMapper {
    override fun invoke(task: Task): TaskItem {
        return TaskItem(
            id = task.id,
            name = task.name,
            description = task.description,
            dates = task.dates.toDateItem(),
            timer = task.timer.toString(),
            capacity = task.capacity.toTimeString(),
            periodicity = task.periodicity.toString(),
            priorityItem = task.priority.naming,
            categoryItem = task.category.naming,
            taskStatus = task.taskStatus.naming
        )
    }

}