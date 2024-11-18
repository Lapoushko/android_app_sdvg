package com.example.android_app_sdvg.presentation.adding.abstract_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android_app_sdvg.presentation.extension.toTimeString
import com.example.android_app_sdvg.presentation.model.task.DatesItem
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar

/**
 * @author Lapoushko
 */
abstract class AbstractAddTaskScreenViewModel(
    private val state: SavedStateHandle,
    private val taskItem: TaskItem? = null
) : ViewModel() {
    var name: String by mutableStateOf(taskItem?.name ?: "")
    var desc: String by mutableStateOf(taskItem?.description ?: "")
    var priority: String by mutableStateOf(taskItem?.priorityItem ?: "")
    var category: String by mutableStateOf(taskItem?.categoryItem ?: "")
    var periodicity: String by mutableStateOf(taskItem?.periodicity ?: "")

    var showModal by mutableStateOf(false)
    private val _dateStart =
        MutableStateFlow<Long?>(state[DATESTART_STATE_TAG] ?: Calendar.getInstance().timeInMillis)
    private val _dateEnd =
        MutableStateFlow<Long?>(state[DATEEND_STATE_TAG] ?: Calendar.getInstance().timeInMillis)
    private val _dates = MutableStateFlow<DatesItem?>(DatesItem(dateStart = _dateStart.value!!, dateEnd = _dateEnd.value!!))
    val dates: StateFlow<DatesItem?> = _dates.asStateFlow()

    var showTimePicker by mutableStateOf(false)
    private var timePickerState: Pair<Int, Int> by mutableStateOf(Pair(0, 0))

    private val _capacity: MutableStateFlow<String> = MutableStateFlow(taskItem?.capacity ?: "0:00")
    val capacity: StateFlow<String> = _capacity.asStateFlow()

    /**
     * Сохранить задачу
     * Здесь происходит валидация введённых значений
     * @param onToBack кнопка для возврата на главный экран
     */
    open fun saveTask(onToBack: () -> Unit) {
    }

    fun updateName(value: String) {
        name = value
    }

    fun updateDesc(value: String) {
        desc = value
    }

    fun updatePriority(value: String) {
        priority = value
    }

    fun updateCategory(value: String) {
        category = value
    }

    fun updatePeriodicity(value: String) {
        periodicity = value
    }

    fun updateDateStart(newDate: Long) {
        _dateStart.value = newDate
        _dates.value = DatesItem(dateStart = newDate, dateEnd = _dateEnd.value!!)
        state[DATESTART_STATE_TAG] = _dateStart.value
    }

    fun updateDateEnd(newDate: Long) {
        _dateEnd.value = newDate
        _dates.value = DatesItem(dateStart = _dateStart.value!!, dateEnd = newDate)
        state[DATEEND_STATE_TAG] = _dateEnd.value
    }

    fun updateTimePickerState(hour: Int, minute: Int) {
        timePickerState = Pair(hour, minute)
        val totalMinutes = hour * 60 + minute
        _capacity.value = totalMinutes.toTimeString()
    }

    fun toggleCalendar() {
        showModal = !showModal
    }

    fun toggleTimer() {
        showTimePicker = !showTimePicker
    }

    companion object {
        private const val DATESTART_STATE_TAG = "DATESTART_STATE_TAG"

        private const val DATEEND_STATE_TAG = "DATEEND_STATE_TAG"
    }
}