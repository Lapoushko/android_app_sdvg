package com.example.android_app_sdvg.presentation.adding.abstracting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android_app_sdvg.presentation.extension.toTimeString
import com.example.android_app_sdvg.presentation.model.task.DatesItem
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import java.util.Calendar

/**
 * @author Lapoushko
 */
abstract class AbstractAddTaskScreenViewModel(
    private val state: SavedStateHandle,
    taskItem: TaskItem? = null
) : ViewModel() {
    private var _taskState = MutableAbstractAddTasScreenState(state = state, taskItem = taskItem)
    val taskState = _taskState as AbstractAddTaskScreenState

    /**
     * Сохранить задачу
     * Здесь происходит валидация введённых значений
     * @param onToBack кнопка для возврата на главный экран
     */
    open fun saveTask(onToBack: () -> Unit) {
    }

    fun updateName(value: String) {
        _taskState.name = value
    }

    fun updateDesc(value: String) {
        _taskState.desc = value
    }

    fun updatePriority(value: String) {
        _taskState.priority = value
    }

    fun updateCategory(value: String) {
        _taskState.category = value
    }

    fun updatePeriodicity(value: String) {
        _taskState.periodicity = value
    }

    fun updateDateStart(newDate: Long) {
        _taskState.dateStart = newDate
        _taskState.dates = DatesItem(dateStart = newDate, dateEnd = _taskState.dateEnd!!)
        state[DATESTART_STATE_TAG] = _taskState.dateStart
    }

    fun updateDateEnd(newDate: Long) {
        _taskState.dateEnd = newDate
        _taskState.dates = DatesItem(dateStart = _taskState.dateStart!!, dateEnd = newDate)
        state[DATEEND_STATE_TAG] = _taskState.dateEnd
    }

    fun updateTimePickerState(hour: Int, minute: Int) {
        _taskState.timePickerState = Pair(hour, minute)
        val totalMinutes = hour * 60 + minute
        _taskState.capacity = totalMinutes.toTimeString()
    }

    fun toggleCalendar() {
        _taskState.showModal = !_taskState.showModal
    }

    fun toggleTimer() {
        _taskState.showTimePicker = !_taskState.showTimePicker
    }

    private class MutableAbstractAddTasScreenState(
        state: SavedStateHandle,
        taskItem: TaskItem? = null
    ) : AbstractAddTaskScreenState {
        override var name: String by mutableStateOf(taskItem?.name ?: "")
        override var desc: String by mutableStateOf(taskItem?.description ?: "")
        override var priority: String by mutableStateOf(taskItem?.priorityItem ?: "")
        override var category: String by mutableStateOf(taskItem?.categoryItem ?: "")
        override var periodicity: String by mutableStateOf(taskItem?.periodicity ?: "")
        override var showModal: Boolean by mutableStateOf(false)
        override var dateStart: Long? by mutableStateOf(
            state[DATESTART_STATE_TAG] ?: Calendar.getInstance().timeInMillis
        )
        override var dateEnd: Long? by mutableStateOf(
            state[DATEEND_STATE_TAG] ?: Calendar.getInstance().timeInMillis
        )
        override var dates: DatesItem by mutableStateOf(
            DatesItem(
                dateStart = dateStart!!,
                dateEnd = dateEnd!!
            )
        )
        override var showTimePicker: Boolean by mutableStateOf(false)
        override var timePickerState: Pair<Int, Int> by mutableStateOf(Pair(0, 0))
        override var capacity: String by mutableStateOf(taskItem?.capacity ?: "0:00")

    }

    companion object {
        private const val DATESTART_STATE_TAG = "DATESTART_STATE_TAG"

        private const val DATEEND_STATE_TAG = "DATEEND_STATE_TAG"
    }
}