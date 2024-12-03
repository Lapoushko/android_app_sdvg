package com.example.android_app_sdvg.domain.usecase.task

import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.domain.repo.TaskRepository

/**
 * @author Lapoushko
 */
interface SubscribeEditTaskUseCase {
    suspend fun editTask(task: Task)
}

class SubscribeEditTaskUseCaseImpl(
    private val repositoryImpl: TaskRepository
) : SubscribeEditTaskUseCase {
    override suspend fun editTask(task: Task) {
        repositoryImpl.editTask(task)
    }

}