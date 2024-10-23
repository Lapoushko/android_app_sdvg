package com.example.android_app_sdvg.domain.usecase

import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.domain.repo.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Lapoushko
 * Юзкейс для всех задач
 */
interface SubscribeTasksUseCase {
    /**
     * получить задачи
     * @return список задач
     */
    suspend fun getTasks(): Flow<List<Task>>
}

/**
 * Реализация юзкейса
 * @param repository репозиторий
 */
class SubscribeTasksUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : SubscribeTasksUseCase {
    override suspend fun getTasks(): Flow<List<Task>> {
        return repository
            .getTasks()
            .map { task ->
                task.ifEmpty {
                    emptyList()
                }
            }
    }

}