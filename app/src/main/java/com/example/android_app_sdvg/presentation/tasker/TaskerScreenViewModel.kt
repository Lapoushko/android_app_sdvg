package com.example.android_app_sdvg.presentation.tasker

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.entity.category.getCategory
import com.example.android_app_sdvg.domain.entity.prioriry.getPriority
import com.example.android_app_sdvg.domain.entity.task.TaskStatus
import com.example.android_app_sdvg.domain.usecase.task.SubscribeDeleteTaskUseCase
import com.example.android_app_sdvg.domain.usecase.task.SubscribeEditTaskUseCase
import com.example.android_app_sdvg.domain.usecase.task.SubscribeTasksUseCase
import com.example.android_app_sdvg.presentation.component.chip.ChipActions
import com.example.android_app_sdvg.presentation.mapper.task.TaskDomainToUiMapper
import com.example.android_app_sdvg.presentation.mapper.task.TaskUiToTaskDomainMapper
import com.example.android_app_sdvg.presentation.model.task.DatesItem
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

    val chips = listOf(
        ChipActions(
            label = "Операции",
            onClick = { toggleBottomSheet() }
        ),
        ChipActions(
            label = "Период",
            onClick = { toggleCalendar() }
        )
    )

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

    fun delete(task: TaskItem) {
        viewModelScope.launch {
            subscribeDeleteTaskUseCase.deleteTask(taskUiToTaskDomainMapper.invoke(task))
            load()
        }
    }

    fun toggleBottomSheet() {
        _state.isNeedToShowBottomSheet = !_state.isNeedToShowBottomSheet
    }

    fun toggleCalendar() {
        _state.isNeedToShowCalendar = !_state.isNeedToShowCalendar
    }

    fun selectDate(newDate: Pair<Long?, Long?>) {
        if (newDate.second == null) {
            _state.selectedDates = DatesItem(newDate.first ?: 0L, newDate.first ?: 0L)
        } else {
            _state.selectedDates = DatesItem(newDate.first ?: 0L, newDate.second!!)
        }
        filterPeriod()
    }

    private fun filterPeriod() {
        _state.tasks = initialList.filter {
            it.dates.dateStart >= state.selectedDates.dateStart
                    && it.dates.dateEnd <= state.selectedDates.dateEnd
        }
    }

    fun sortByQuery(text: String){
        when(text){
            PRIORITY_KEY -> _state.tasks = state.tasks.sortedByDescending { it.priorityItem.getPriority()?.value }
            CATEGORY_KEY -> _state.tasks = state.tasks.sortedByDescending { it.categoryItem.getCategory()?.value }
        }
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

    companion object{
        const val PRIORITY_KEY = "Приоритет"
        const val CATEGORY_KEY = "Категория"
    }

    private class MutableTaskerScreenState : TaskerScreenState {
        override var tasks: List<TaskItem> by mutableStateOf(emptyList())
        override var isNeedToShowBottomSheet: Boolean by mutableStateOf(false)
        override var isNeedToShowCalendar: Boolean by mutableStateOf(false)
        override var selectedDates: DatesItem by mutableStateOf(DatesItem(0, 0))
    }
}