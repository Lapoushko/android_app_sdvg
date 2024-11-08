package com.example.android_app_sdvg.presentation.tasker

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.usecase.SubscribeDeleteTaskUseCase
import com.example.android_app_sdvg.domain.usecase.SubscribeTasksUseCase
import com.example.android_app_sdvg.presentation.mapper.TaskToUiMapper
import com.example.android_app_sdvg.presentation.mapper.TaskUiToTaskMapper
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class TaskerScreenViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val subscribeTasksUseCase: SubscribeTasksUseCase,
    private val subscribeDeleteTaskUseCase: SubscribeDeleteTaskUseCase,
    private val uiMapper: TaskToUiMapper,
    private val taskUiToTaskMapper: TaskUiToTaskMapper
) : ViewModel() {
    var showModal by mutableStateOf(false)

    private val _selectedDate =
        MutableStateFlow<Long?>(state[STATE_TAG] ?: Calendar.getInstance().timeInMillis)

    private var _tasks: MutableStateFlow<List<TaskItem>> = MutableStateFlow(emptyList())
    val tasks: StateFlow<List<TaskItem>> = _tasks

    init {
        Log.d(Constants.LOG_KEY, "Init ${this::class.simpleName}")
        load()
    }

    private fun load(){
        viewModelScope.launch {
            _tasks.value =
                subscribeTasksUseCase
                    .getTasks()
                    .map { uiMapper(it) }
            state[STATE_TAG] = _selectedDate.value
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(Constants.LOG_KEY, "onCleared ${this::class.simpleName}")
    }

    fun selectDate(newDate: Long) {
        _selectedDate.value = newDate
        state[STATE_TAG] = _selectedDate.value
    }

    fun getDate(): Long = _selectedDate.value ?: Calendar.getInstance().timeInMillis

    fun toggleCalendar() {
        showModal = !showModal
    }

    fun delete(task: TaskItem) {
        viewModelScope.launch {
            subscribeDeleteTaskUseCase.deleteTask(taskUiToTaskMapper.invoke(task))
            load()
        }
    }

    companion object {
        private const val STATE_TAG = "STATE_TAG"
    }
}