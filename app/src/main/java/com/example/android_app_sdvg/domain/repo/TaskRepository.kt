package com.example.android_app_sdvg.domain.repo

import com.example.android_app_sdvg.domain.entity.task.Task

/**
 * @author Lapoushko
 * репозиторий задач
 */
interface TaskRepository {
    /**
     * получить Задачи
     * @return список задач
     */
    suspend fun getTasks(): List<Task>

    /**
     * получить задачу по айди
     * @param id айди
     * @return задача
     */
    suspend fun getTaskById(id: Long) : Task

    /**
     * вставить задачу
     * @param task задача
     */
    suspend fun insertTask(task: Task)

    /**
     * Удаление задачи
     * @param task задача
     */
    suspend fun deleteTask(task: Task)

    /**
     * Редактировать задачу
     * @param task редактировать
     */
    suspend fun editTask(task: Task)
}
