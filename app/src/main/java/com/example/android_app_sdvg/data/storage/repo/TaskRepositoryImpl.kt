package com.example.android_app_sdvg.data.storage.repo

import com.example.android_app_sdvg.data.storage.dao.TaskDao
import com.example.android_app_sdvg.data.storage.mapper.task.TaskDbToTaskMapper
import com.example.android_app_sdvg.data.storage.mapper.task.TaskToTaskDbMapper
import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.domain.repo.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
    override suspend fun getTasks(): List<Task> {
        return withContext(Dispatchers.IO) {
            dao.getTasks().map { task -> mapperTaskDbToTask.invoke(task) }
        }
    }

    /**
     * получить задачу по айди
     * @param id айди
     * @return задача
     */
    override suspend fun getTaskById(id: Long): Task {
        return withContext(Dispatchers.IO){
            mapperTaskDbToTask.invoke(dao.getTaskById(id))
        }
    }

    /**
     * вставить задачу
     * @param task задача
     */
    override suspend fun insertTask(task: Task) {
        withContext(Dispatchers.IO) {
            dao.insertTask(mapperTaskToTaskDb.invoke(task))
        }
    }

    /**
     * Удаление задачи
     * @param task задача
     */
    override suspend fun deleteTask(task: Task) {
        withContext(Dispatchers.IO) {
            val taskDb = mapperTaskToTaskDb.invoke(task)
            dao.deleteTask(
                taskDb.name ?: "",
                taskDb.description ?: "",
                dates = task.dates
            )
        }
    }

    /**
     * Редактировать задачу
     * @param task редактировать
     */
    override suspend fun editTask(task: Task) {
        withContext(Dispatchers.IO){
            val taskDb = mapperTaskToTaskDb.invoke(task)
            dao.updateTask(
                taskDb = taskDb
            )
        }
    }
}