package com.example.android_app_sdvg.data.storage.dao

import com.example.android_app_sdvg.data.storage.entity.TaskDb
import com.example.android_app_sdvg.data.storage.mock.MockTasks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Lapoushko
 * Dao для задач
 */
interface TaskDao {
    /**
     * Получить таски
     * @return flow таски
     */
    suspend fun getTasks(): List<TaskDb>

    /**
     * вставить в базу данных задачу
     * @param taskDb задача
     */
    suspend fun insertTask(taskDb: TaskDb)

    /**
     * удалить задачу
     */
    suspend fun deleteTask(taskDb: TaskDb)
}

/**
 * Реализация одноимённого интерфейса
 */
class TaskDaoImpl @Inject constructor() : TaskDao {
    val tasks = MockTasks().tasks

    override suspend fun getTasks(): List<TaskDb> = tasks

    override suspend fun insertTask(taskDb: TaskDb) {
        tasks.add(taskDb)
    }

    override suspend fun deleteTask(taskDb: TaskDb) {
        tasks.remove(taskDb)
    }
}