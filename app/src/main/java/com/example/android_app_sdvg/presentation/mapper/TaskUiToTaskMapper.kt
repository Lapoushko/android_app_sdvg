package com.example.android_app_sdvg.presentation.mapper

import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import javax.inject.Inject

/**
 * @author Lapoushko
 * маппер из ui в task
 */
interface TaskUiToTaskMapper {
    /**
     * перевод
     * @param taskUi задача из ui
     * @return задача из domain
     */
    operator fun invoke(taskUi: TaskItem) : Task
}

class TaskUiToTaskMapperImpl @Inject constructor(): TaskUiToTaskMapper{
    /**
     * перевод
     * @param taskUi задача из ui
     * @return задача из domain
     */
    override fun invoke(taskUi: TaskItem): Task {
        return Task(
            name = taskUi.name,
            description = taskUi.description,
            dateStart = taskUi.dateStart,
            timer = taskUi.timer,
            capacity = taskUi.capacity,
            periodicity = taskUi.periodicity,
            priority = taskUi.priorityItem,
            category = taskUi.categoryItem
        )
    }


}
