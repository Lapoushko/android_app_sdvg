package com.example.android_app_sdvg.presentation.tasker

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Calendar

/**
 * @author Lapoushko
 */
class TaskerScreenViewModel : ViewModel(){
    var showModal by mutableStateOf(false)
    var selectedDate by mutableStateOf<Long?>(Calendar.getInstance().timeInMillis)

    private var _tasks: MutableStateFlow<List<TaskItem>> = MutableStateFlow(MockRepo().tasks)
    val tasks: StateFlow<List<TaskItem>> = _tasks

    init {
        Log.d(Constants.LOG_KEY, "Init ${this::class.simpleName}")
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