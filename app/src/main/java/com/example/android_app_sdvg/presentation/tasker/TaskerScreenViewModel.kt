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
import com.example.android_app_sdvg.presentation.component.chip.ChipActions
import com.example.android_app_sdvg.presentation.extension.toDateString
import com.example.android_app_sdvg.presentation.extension.toNeedTime
import com.example.android_app_sdvg.presentation.mapper.task.TaskDomainToUiMapper
import com.example.android_app_sdvg.presentation.mapper.task.TaskUiToTaskDomainMapper
import com.example.android_app_sdvg.presentation.model.task.DatesItem
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val initialChipAction = ChipActions(
        label = "Период",
        onClick = { toggleCalendar() }
    )
    private var _chipsState = MutableStateFlow(listOf(initialChipAction))
    val chipsState = _chipsState.asStateFlow()

    private val _state = MutableTaskerScreenState()
    val state = _state as TaskerScreenState

    private var initialTasks: Map<String, List<TaskItem>> = emptyMap()

    init {
        Log.d(Constants.LOG_KEY, "Init ${this::class.simpleName}")
        load()
    }

    private fun load() {
        viewModelScope.launch {
            val tasks =
                subscribeTasksUseCase
                    .getTasks()
                    .map { uiMapper(it) }
                    .sortedBy { it.dates.dateStart }

            val tasksWithDates = HashMap<String, List<TaskItem>>()

            tasks.forEach { task ->
                val startDay = task.dates.dateStart.toNeedTime().toDateString()
                tasksWithDates[startDay] = tasks.filter {
                    task.dates.dateStart.toNeedTime() == it.dates.dateStart.toNeedTime()
                }
            }
            _state.tasksWithDates = sortByData(tasksWithDates)

            state.tasksWithDates.forEach { println("${it.key} - ${it.value}") }
            initialTasks = state.tasksWithDates
        }
    }

    private fun sortByData(tasksWithDates: Map<String, List<TaskItem>>) : Map<String, List<TaskItem>>{
        return tasksWithDates.toList()
            .sortedBy { (_, value) -> value.minOf { it.dates.dateStart } }
            .toMap()
    }

    fun toggleCalendar() {
        _state.isNeedToShowCalendar = !_state.isNeedToShowCalendar
    }

    fun setStatus(task: TaskItem, status: TaskStatus) {
        editStatusTask(task, status)
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
            _state.tasksWithDates = state.tasksWithDates.mapValues { tasks ->
                tasks.value.filterNot { it == task }
            }

//            _state.tasksWithDates = _state.tasksWithDates.filterNot { it == task }
        }
    }

    fun filterByDate(newDate: Pair<Long?, Long?>) {
        if (newDate.first != null && newDate.second != null) {
            _state.selectedDates = DatesItem(newDate.first ?: 0L, newDate.second ?: 0L)
            filterPeriod()
        }
    }

    private fun filterPeriod() {
        addChipDate()

        _state.tasksWithDates = initialTasks.mapValues { entry ->
            entry.value.filter {
                it.dates.dateStart >= state.selectedDates.dateStart &&
                        it.dates.dateStart <= state.selectedDates.dateEnd
            }
        }

        //        _state.tasks = initialTasks.filter {
//            it.dates.dateStart >= state.selectedDates.dateStart
//                    && it.dates.dateEnd <= state.selectedDates.dateEnd
//        }
    }

    private fun addChipDate(
    ) {
        val text =
            "${state.selectedDates.dateStart.toDateString()} - ${state.selectedDates.dateEnd.toDateString()}"
        val newChip = ChipActions(
            label = text,
            onClick = {
                resetFilter()
            },
            isAdded = true
        )
        _chipsState.value = listOf(newChip)
    }

    private fun resetFilter() {
        viewModelScope.launch {
            load()
            _chipsState.value = listOf(initialChipAction)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(Constants.LOG_KEY, "onCleared ${this::class.simpleName}")
    }

    private class MutableTaskerScreenState : TaskerScreenState {
        override var tasksWithDates: Map<String, List<TaskItem>> by mutableStateOf(emptyMap())
        override var isNeedToShowBottomSheet: Boolean by mutableStateOf(false)
        override var isNeedToShowCalendar: Boolean by mutableStateOf(false)
        override var selectedDates: DatesItem by mutableStateOf(DatesItem(0, Long.MAX_VALUE))
    }
}


