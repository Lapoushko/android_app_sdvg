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
     * @param taskDb задачи
     * @return Список задач domain
     */
    suspend operator fun invoke(taskDb: TaskDb): Task
}

/**
 * Реализация одноименного интерфейса
 */
class TaskDbToTaskMapperImpl @Inject constructor() : TaskDbToTaskMapper {
    override suspend fun invoke(taskDb: TaskDb): Task {
        return Task(
            name = taskDb.name ?: "Нет названия",
            description = taskDb.description ?: "Нет описания",
            timer = taskDb.timer ?: 0L,
            dateStart = taskDb.dateStart ?: 0L,
            dateEnd = taskDb.dateEnd ?: 0L,
            capacity = taskDb.capacity ?: 0L,
            periodicity = taskDb.periodicity ?: 0,
            priority = taskDb.priority ?: Priority.HIGH,
            category = taskDb.category ?: Category.STANDARD
        )
    }
}