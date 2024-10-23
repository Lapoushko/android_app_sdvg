package com.example.android_app_sdvg.presentation.tasker

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.usecase.SubscribeTasksUseCase
import com.example.android_app_sdvg.presentation.mapper.TaskToUiMapper
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class TaskerScreenViewModel @Inject constructor(
    private val subscribeTasksUseCase: SubscribeTasksUseCase,
    private val uiMapper: TaskToUiMapper
) : ViewModel(){
    var showModal by mutableStateOf(false)
    var selectedDate by mutableStateOf<Long?>(Calendar.getInstance().timeInMillis)

    private var _tasks: MutableStateFlow<List<TaskItem>> = MutableStateFlow(emptyList())
    val tasks: StateFlow<List<TaskItem>> = _tasks

    init {
        Log.d(Constants.LOG_KEY, "Init ${this::class.simpleName}")
        viewModelScope.launch {
            _tasks.value =
                subscribeTasksUseCase
                    .getTasks()
                    .first()
                    .map { uiMapper(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(Constants.LOG_KEY, "onCleared ${this::class.simpleName}")
    }

    fun selectDate(newDate: Long){
        selectedDate = newDate
    }

    fun toggleCalendar(){
        showModal = !showModal
    }
}