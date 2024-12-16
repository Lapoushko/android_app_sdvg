package com.example.android_app_sdvg.presentation.tasker

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.entity.task.TaskStatus
import com.example.android_app_sdvg.domain.usecase.task.SubscribeDeleteTaskUseCase
import com.example.android_app_sdvg.domain.usecase.task.SubscribeEditTaskUseCase
import com.example.android_app_sdvg.domain.usecase.task.SubscribeTasksUseCase
import com.example.android_app_sdvg.presentation.mapper.task.TaskDomainToUiMapper
import com.example.android_app_sdvg.presentation.mapper.task.TaskUiToTaskDomainMapper
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class TaskerScreenViewModel @Inject constructor(
    private val subscribeTasksUseCase: SubscribeTasksUseCase,
    private val subscribeDeleteTaskUseCase: SubscribeDeleteTaskUseCase,
    private val subscribeEditTaskUseCase: SubscribeEditTaskUseCase,
    private val uiMapper: TaskDomainToUiMapper,
    private val taskUiToTaskDomainMapper: TaskUiToTaskDomainMapper
) : ViewModel() {
    private val _state = MutableTaskerScreenState()
    val state = _state as TaskerScreenState
    private var initialList = listOf<TaskItem>()

    init {
        Log.d(Constants.LOG_KEY, "Init ${this::class.simpleName}")
        load()
    }

    private fun load() {
        viewModelScope.launch {
            _state.tasks =
                subscribeTasksUseCase
                    .getTasks()
                    .map { uiMapper(it) }
            initialList = _state.tasks
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(Constants.LOG_KEY, "onCleared ${this::class.simpleName}")
    }

    fun completeTask(task: TaskItem) {
        editStatusTask(task, TaskStatus.COMPLETED)
    }

    private fun editStatusTask(
        task: TaskItem,
        status: TaskStatus
    ) {
        viewModelScope.launch {
            subscribeEditTaskUseCase.editTask(
                task = taskUiToTaskDomainMapper(
                    task.copy(
                        taskStatus = status.naming
                    )
                )
            )
            load()
        }
    }

    fun delete(task: TaskItem) {
        viewModelScope.launch {
            subscribeDeleteTaskUseCase.deleteTask(taskUiToTaskDomainMapper.invoke(task))
            load()
        }
    }

    private class MutableTaskerScreenState : TaskerScreenState {
        override var tasks: List<TaskItem> by mutableStateOf(emptyList())
    }
}