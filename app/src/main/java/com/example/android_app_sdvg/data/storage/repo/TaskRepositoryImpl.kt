package com.example.android_app_sdvg.data.storage.repo

import com.example.android_app_sdvg.data.storage.dao.TaskDao
import com.example.android_app_sdvg.data.storage.mapper.TaskDbToTaskMapper
import com.example.android_app_sdvg.data.storage.mapper.TaskToTaskDbMapper
import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.domain.repo.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Lapoushko
 *
 * Реалзиация задач
 * @param dao дао для задач
 * @param mapperTaskDbToTask маппер
 */
class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao,
    private val mapperTaskDbToTask: TaskDbToTaskMapper,
    private val mapperTaskToTaskDb: TaskToTaskDbMapper
) : TaskRepository {
    override suspend fun getTasks(): Flow<List<Task>> {
        return flow { emit(mapperTaskDbToTask.invoke(tasks = dao.getTasks().first())) }
    }

    /**
     * вставить задачу
     * @param task задача
     */
    override suspend fun insertTask(task: Flow<Task>) {
        dao.insertTask(flow { emit(mapperTaskToTaskDb.invoke(task.first())) })
    }
}