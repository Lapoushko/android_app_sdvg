package com.example.android_app_sdvg.presentation.create_task

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.usecase.SubscribeInsertTaskUseCase
import com.example.android_app_sdvg.presentation.extension.toDateString
import com.example.android_app_sdvg.presentation.mapper.TaskUiToTaskMapper
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 * Вью модель для экрана создания зхадачи
 */
@HiltViewModel
class CreateTaskScreenViewModel @Inject constructor(
    private val useCase: SubscribeInsertTaskUseCase,
    private val mapper: TaskUiToTaskMapper
) : ViewModel() {
    var name: String by mutableStateOf("")
    var desc: String by mutableStateOf("")
    var time: String by mutableStateOf("0")
    var priority by mutableStateOf("")
    var category: String by mutableStateOf("")
    var periodicity: String by mutableStateOf("")
    var dateStart: Long by mutableLongStateOf(0L)

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
            when (name.isNotEmpty() || desc.isNotEmpty()) {
                true -> {
                    val task = TaskItem(
                        name = name,
                        description = desc,
                        dateStart = dateStart.toDateString(),
                        timer = time,
                        capacity = "0",
                        periodicity = periodicity,
                        priorityItem = priority,
                        categoryItem = category
                    )
                    useCase.insertTask(mapper.invoke(task))
                    onToBack()
                    Log.d(Constants.LOG_KEY, task.toString())
                }
                false -> {
                    Log.d(Constants.LOG_KEY, "Заполните полностью данные")
                }
            }
        }
    }

    fun updateName(value: String) {
        name = value
    }

    fun updateDesc(value: String) {
        desc = value
    }

    fun updateTime(value: String) {
        time = value
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
}