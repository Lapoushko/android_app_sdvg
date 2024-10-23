package com.example.android_app_sdvg.data.storage.dao

import com.example.android_app_sdvg.data.storage.entity.TaskDb
import com.example.android_app_sdvg.data.storage.mock.MockTasks
import kotlinx.coroutines.flow.Flow
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
    suspend fun getTasks() : Flow<List<TaskDb>>
}

/**
 * Реализация одноимённого интерфейса
 */
class TaskDaoImpl @Inject constructor(): TaskDao{
    override suspend fun getTasks(): Flow<List<TaskDb>> = flow { emit(MockTasks().tasks) }
}