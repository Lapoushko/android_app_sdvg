package com.example.android_app_sdvg.domain.repo

import com.example.android_app_sdvg.domain.entity.task.Task
import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 * репозиторий задач
 */
interface TaskRepository {
    /**
     * получить Задачи
     * @return список задач
     */
    suspend fun getTasks(): Flow<List<Task>>

    /**
     * вставить задачу
     * @param task задача
     */
    suspend fun insertTask(task: Flow<Task>)
}
