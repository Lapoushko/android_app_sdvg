package com.example.android_app_sdvg.domain.usecase.task

import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.domain.repo.TaskRepository
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
    suspend fun getTasks(): List<Task>
}

/**
 * Реализация юзкейса
 * @param repository репозиторий
 */
class SubscribeTasksUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : SubscribeTasksUseCase {
    override suspend fun getTasks(): List<Task> {
        return repository
            .getTasks()


//        return repository
//            .getTasks()
//            .map { task ->
//                task.ifEmpty {
//                    emptyList()
//                }
//            }
    }

}