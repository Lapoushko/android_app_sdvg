package com.example.android_app_sdvg.presentation.create_task

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.usecase.SubscribeInsertTaskUseCase
import com.example.android_app_sdvg.presentation.extension.toDateString
import com.example.android_app_sdvg.presentation.extension.toTimeString
import com.example.android_app_sdvg.presentation.mapper.TaskUiToTaskMapper
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject
import kotlin.math.min

/**
 * @author Lapoushko
 * Вью модель для экрана создания зхадачи
 */
@HiltViewModel
class CreateTaskScreenViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val state: SavedStateHandle,
    private val useCase: SubscribeInsertTaskUseCase,
    private val mapper: TaskUiToTaskMapper
) : ViewModel() {
    var name: String by mutableStateOf("")
    var desc: String by mutableStateOf("")
    var priority: String by mutableStateOf("")
    var category: String by mutableStateOf("")
    var periodicity: String by mutableStateOf("")

    var showModal by mutableStateOf(false)
    private val _dateStart =
        MutableStateFlow<Long?>(state[DATESTART_STATE_TAG] ?: Calendar.getInstance().timeInMillis)
    val dateStart: StateFlow<Long?> = _dateStart
    private val _dateEnd =
        MutableStateFlow<Long?>(state[DATEEND_STATE_TAG] ?: Calendar.getInstance().timeInMillis)
    val dateEnd: StateFlow<Long?> = _dateEnd

    var showTimePicker by mutableStateOf(false)
    private var timePickerState: Pair<Int, Int> by mutableStateOf(Pair(0, 0))

    private val _capacity: MutableStateFlow<String> = MutableStateFlow("0:00")
    val capacity: StateFlow<String> = _capacity.asStateFlow()

    init {
        Log.d(Constants.LOG_KEY, "Init ${this::class.simpleName}")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(Constants.LOG_KEY, "onCleared ${this::class.simpleName}")
    }

    /**
     * Сохранить задачу
     * Здесь происходит валидация введённых значений
     * @param onToBack кнопка для возврата на главный экран
     */
    fun saveTask(onToBack: () -> Unit) {
        viewModelScope.launch {
            if (name.isNotEmpty() || desc.isNotEmpty()) {
                if (dateEnd.value!! < dateStart.value!!) {
                    Toast.makeText(
                        context,
                        "Дата окончания не может быть раньше даты начала",
                        Toast.LENGTH_LONG
                    ).show()
                    return@launch
                }

                val task = TaskItem(
                    name = name,
                    description = desc,
                    dateStart = dateStart.value!!.toDateString(),
                    dateEnd = dateEnd.value!!.toDateString(),
                    timer = "",
                    capacity = capacity.value,
                    periodicity = periodicity,
                    priorityItem = priority,
                    categoryItem = category
                )
                useCase.insertTask(mapper.invoke(task))
                onToBack()
            } else {
                Toast.makeText(context, "Заполните полностью данные", Toast.LENGTH_LONG).show()
            }
        }
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
        state[DATESTART_STATE_TAG] = _dateStart.value
    }

    fun updateDateEnd(newDate: Long) {
        _dateEnd.value = newDate
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