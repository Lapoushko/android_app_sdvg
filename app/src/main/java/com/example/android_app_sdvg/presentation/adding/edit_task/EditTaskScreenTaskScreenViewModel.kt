package com.example.android_app_sdvg.presentation.adding.edit_task

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.usecase.SubscribeEditTaskUseCase
import com.example.android_app_sdvg.presentation.adding.abstract_model.AbstractAddTaskScreenViewModel
import com.example.android_app_sdvg.presentation.mapper.TaskUiToTaskMapper
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 * вью модель для редактирования задачи
 */
@HiltViewModel
class EditTaskScreenTaskScreenViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val state: SavedStateHandle,
    private val useCase: SubscribeEditTaskUseCase,
    private val mapper: TaskUiToTaskMapper
) : AbstractAddTaskScreenViewModel(
    state = state
) {
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
    override fun saveTask(onToBack: () -> Unit) {
        viewModelScope.launch {
            if (name.isNotEmpty() || desc.isNotEmpty()) {
                if (dates.value!!.dateEnd < dates.value!!.dateStart) {
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
                    dates = dates.value!!,
                    timer = "",
                    capacity = capacity.value,
                    periodicity = periodicity,
                    priorityItem = priority,
                    categoryItem = category
                )
                useCase.editTask(mapper.invoke(task))
                onToBack()
            } else {
                Toast.makeText(context, "Заполните полностью данные", Toast.LENGTH_LONG).show()
            }
        }
    }
}