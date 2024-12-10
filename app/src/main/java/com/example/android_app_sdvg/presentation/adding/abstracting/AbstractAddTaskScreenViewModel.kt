package com.example.android_app_sdvg.presentation.adding.abstracting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android_app_sdvg.presentation.extension.toTimeString
import com.example.android_app_sdvg.presentation.model.input.Input
import com.example.android_app_sdvg.presentation.model.input.TaskErrors
import com.example.android_app_sdvg.presentation.model.input.checkErrorInput
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

    fun updateName(input: String) {
        val error = TaskErrors.NAME_ERROR
        _taskState.name = input.checkErrorInput(
            error = error,
            adding = { _taskState.errors.add(error) },
            removing = { _taskState.errors.remove(error) },
            isCorrect = input.isNotEmpty()
        )
    }

    fun updateDesc(input: String) {
//        val error = TaskErrors.DESC_ERROR
        val error = null
        _taskState.desc = input.checkErrorInput(
            error = error,
//            adding = { _taskState.errors.add(error) },
//            removing = { _taskState.errors.remove(error) },
            isCorrect = input.isNotEmpty()
        )
    }

    fun updatePriority(input: String) {
        val error = TaskErrors.PRIORITY_ERROR
//        val error = null
        _taskState.priority = input.checkErrorInput(
            error = error,
            adding = { _taskState.errors.add(error) },
            removing = { _taskState.errors.remove(error) },
            isCorrect = input.isNotEmpty()
        )
    }

    fun updateCategory(input: String) {
        val error = TaskErrors.CATEGORY_ERROR
        _taskState.category = input.checkErrorInput(
            error = error,
            adding = { _taskState.errors.add(error) },
            removing = { _taskState.errors.remove(error) },
            isCorrect = input.isNotEmpty()
        )
    }

    fun updatePeriodicity(input: String) {
        val error = TaskErrors.PERIODICITY_ERROR
        _taskState.periodicity = input.checkErrorInput(
            error = error,
            adding = { _taskState.errors.add(error) },
            removing = { _taskState.errors.remove(error) },
            isCorrect = (input.isNotEmpty() && input.isDigitsOnly())
        )
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
        override var name: Input<TaskErrors> by mutableStateOf(
            Input(
                text = taskItem?.name ?: "",
                error = TaskErrors.NAME_ERROR
            )
        )
        override var desc: Input<TaskErrors> by mutableStateOf(
            Input(
                text = taskItem?.description ?: "", error = null
            )
        )
        override var priority: Input<TaskErrors> by mutableStateOf(
            Input(
                text = taskItem?.priorityItem ?: "", error = TaskErrors.PRIORITY_ERROR
            )
        )
        override var category: Input<TaskErrors> by mutableStateOf(
            Input(
                text = taskItem?.categoryItem ?: "", error = TaskErrors.CATEGORY_ERROR
            )
        )
        override var periodicity: Input<TaskErrors> by mutableStateOf(
            Input(
                text = taskItem?.periodicity ?: "", error = TaskErrors.PERIODICITY_ERROR
            )
        )
        override var showModal: Boolean by mutableStateOf(false)
        override var dateStart: Long? by mutableStateOf(
            state[DATESTART_STATE_TAG] ?: Calendar.getInstance().timeInMillis
        )
        override var dateEnd: Long? by mutableStateOf(
            state[DATEEND_STATE_TAG] ?: Calendar.getInstance().timeInMillis
        )
        override var dates: DatesItem by mutableStateOf(
            DatesItem(
                dateStart = dateStart ?: 0L,
                dateEnd = dateEnd ?: 0L
            )
        )
        override var showTimePicker: Boolean by mutableStateOf(false)
        override var timePickerState: Pair<Int, Int> by mutableStateOf(Pair(0, 0))
        override var capacity: String by mutableStateOf(taskItem?.capacity ?: "0:00")
        override var errors: MutableSet<TaskErrors> by mutableStateOf(TaskErrors.entries.toMutableSet())
    }

    companion object {
        private const val DATESTART_STATE_TAG = "DATESTART_STATE_TAG"

        private const val DATEEND_STATE_TAG = "DATEEND_STATE_TAG"
    }
}