package com.example.android_app_sdvg.data.storage.mapper

import com.example.android_app_sdvg.data.storage.entity.TaskDb
import com.example.android_app_sdvg.domain.entity.category.Category
import com.example.android_app_sdvg.domain.entity.prioriry.Priority
import com.example.android_app_sdvg.domain.entity.task.Dates
import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.presentation.model.task.DatesItem
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
            id = taskDb.id,
            name = taskDb.name ?: "Нет названия",
            description = taskDb.description ?: "Нет описания",
            timer = taskDb.timer ?: 0L,
            dates = taskDb.dates ?: Dates(0,0),
            capacity = taskDb.capacity ?: 0,
            periodicity = taskDb.periodicity ?: 0,
            priority = taskDb.priority ?: Priority.HIGH,
            category = taskDb.category ?: Category.STANDARD
        )
    }
}