package com.example.android_app_sdvg.data.storage.mapper.task

import com.example.android_app_sdvg.data.storage.entity.TaskDb
import com.example.android_app_sdvg.domain.entity.task.Task
import javax.inject.Inject

/**
 * @author Lapoushko
 * маппер из domain в db
 */
interface TaskToTaskDbMapper {
    /**
     * перевод
     * @param task задача domain
     * @return задача из бд
     */
    suspend operator fun invoke(task: Task): TaskDb
}

class TaskToTaskDbMapperImpl @Inject constructor() : TaskToTaskDbMapper {
    /**
     * перевод
     * @param task задача domain
     * @return задача из бд
     */
    override suspend fun invoke(task: Task): TaskDb {
        return TaskDb(
            id = task.id,
            name = task.name,
            description = task.description,
            dates = task.dates,
            timer = task.timer,
            capacity = task.capacity,
            periodicity = task.periodicity,
            priority = task.priority,
            category = task.category,
            taskStatus = task.taskStatus
        )
    }
}