package com.example.android_app_sdvg.domain.usecase

import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.domain.repo.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 * юзкейс вставки новой задачи
 */
interface SubscribeInsertTaskUseCase {
    suspend fun insertTask(task: Flow<Task>)
}

class SubscribeInsertTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
): SubscribeInsertTaskUseCase{
    /**
     * получить задачи
     * @return список задач
     */
    override suspend fun insertTask(task: Flow<Task>) {
        repository.insertTask(task)
    }

}