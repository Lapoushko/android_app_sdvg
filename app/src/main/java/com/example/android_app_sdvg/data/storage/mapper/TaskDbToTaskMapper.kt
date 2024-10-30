package com.example.android_app_sdvg.data.storage.mapper

import com.example.android_app_sdvg.data.storage.entity.TaskDb
import com.example.android_app_sdvg.domain.entity.category.Category
import com.example.android_app_sdvg.domain.entity.prioriry.Priority
import com.example.android_app_sdvg.domain.entity.task.Task
import javax.inject.Inject

/**
 * @author Lapoushko
 *
 * Маппер для перевода таски из бд в обычную таску из domain
 */
interface TaskDbToTaskMapper {
    /**
     * Изменить тип данных
     * @param tasks задачи
     * @return Список задач domain
     */
    suspend operator fun invoke(tasks: List<TaskDb>): List<Task>
}

/**
 * Реализация одноименного интерфейса
 */
class TaskDbToTaskMapperImpl @Inject constructor() : TaskDbToTaskMapper {
    override suspend fun invoke(tasks: List<TaskDb>): List<Task> {
        return tasks.map { task ->
            Task(
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
}