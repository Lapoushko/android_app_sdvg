package com.example.android_app_sdvg.domain.usecase

import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.domain.repo.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 * юз кейс удаления задачи
 */
interface SubscribeDeleteTaskUseCase {
    suspend fun deleteTask(task: Task)
}

class SubscribeDeleteTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
): SubscribeDeleteTaskUseCase{
    override suspend fun deleteTask(task: Task) {
        repository.deleteTask(task)
    }
}