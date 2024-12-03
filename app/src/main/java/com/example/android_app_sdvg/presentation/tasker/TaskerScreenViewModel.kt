package com.example.android_app_sdvg.presentation.tasker

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.usecase.task.SubscribeDeleteTaskUseCase
import com.example.android_app_sdvg.domain.usecase.task.SubscribeTasksUseCase
import com.example.android_app_sdvg.presentation.mapper.task.TaskDomainToUiMapper
import com.example.android_app_sdvg.presentation.mapper.task.TaskUiToTaskDomainMapper
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class TaskerScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val subscribeTasksUseCase: SubscribeTasksUseCase,
    private val subscribeDeleteTaskUseCase: SubscribeDeleteTaskUseCase,
    private val uiMapper: TaskDomainToUiMapper,
    private val taskUiToTaskDomainMapper: TaskUiToTaskDomainMapper
) : ViewModel() {
    private val _state = MutableTaskerScreenState(savedStateHandle = savedStateHandle)
    val state = _state as TaskerScreenState

    init {
        Log.d(Constants.LOG_KEY, "Init ${this::class.simpleName}")
        load()
    }

    private fun load(){
        viewModelScope.launch {
            _state.tasks =
                subscribeTasksUseCase
                    .getTasks()
                    .map { uiMapper(it) }
            savedStateHandle[STATE_TAG] = _state.selectedDate
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(Constants.LOG_KEY, "onCleared ${this::class.simpleName}")
    }

    fun selectDate(newDate: Long) {
        _state.selectedDate = newDate
        savedStateHandle[STATE_TAG] = _state.selectedDate
    }

    fun toggleCalendar() {
        _state.showModal = !_state.showModal
    }

    fun delete(task: TaskItem) {
        viewModelScope.launch {
            subscribeDeleteTaskUseCase.deleteTask(taskUiToTaskDomainMapper.invoke(task))
            load()
        }
    }

    private class MutableTaskerScreenState(
        savedStateHandle: SavedStateHandle
    ): TaskerScreenState{
        override var showModal: Boolean by mutableStateOf(false)
        override var selectedDate: Long? by mutableStateOf(savedStateHandle[STATE_TAG] ?: Calendar.getInstance().timeInMillis)
        override var tasks: List<TaskItem> by mutableStateOf(emptyList())
    }

    companion object {
        private const val STATE_TAG = "STATE_TAG"
    }
}