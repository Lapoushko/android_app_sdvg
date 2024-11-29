package com.example.android_app_sdvg.presentation.adding.editor

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.usecase.task.SubscribeEditTaskUseCase
import com.example.android_app_sdvg.presentation.adding.abstracting.AbstractAddTaskScreenViewModel
import com.example.android_app_sdvg.presentation.mapper.task.TaskUiToTaskDomainMapper
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 * вью модель для редактирования задачи
 */
@HiltViewModel
class EditTaskScreenTaskScreenViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    state: SavedStateHandle,
    private val useCase: SubscribeEditTaskUseCase,
    private val mapper: TaskUiToTaskDomainMapper,
) : AbstractAddTaskScreenViewModel(
    state = state,
) {

    private var _taskItem : MutableStateFlow<TaskItem?> = MutableStateFlow(null)

    init {
        Log.d(Constants.LOG_KEY, "Init ${this::class.simpleName}")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(Constants.LOG_KEY, "onCleared ${this::class.simpleName}")
    }

    fun updateTask(taskItem: TaskItem){
        _taskItem.value = taskItem
    }

    /**
     * Сохранить задачу
     * Здесь происходит валидация введённых значений
     * @param onToBack кнопка для возврата на главный экран
     */
    override fun saveTask(onToBack: () -> Unit) {
        viewModelScope.launch {
            if (taskState.name.isNotEmpty() || taskState.desc.isNotEmpty()) {
                if (taskState.dates.dateEnd < taskState.dates.dateStart) {
                    Toast.makeText(
                        context,
                        "Дата окончания не может быть раньше даты начала",
                        Toast.LENGTH_LONG
                    ).show()
                    return@launch
                }

                val task = TaskItem(
                    id = _taskItem.value?.id,
                    name = taskState.name,
                    description = taskState.desc,
                    dates = taskState.dates,
                    timer = "",
                    capacity = taskState.capacity,
                    periodicity = taskState.periodicity,
                    priorityItem = taskState.priority,
                    categoryItem = taskState.category
                )
                useCase.editTask(mapper.invoke(task))
                onToBack()
            } else {
                Toast.makeText(context, "Заполните полностью данные", Toast.LENGTH_LONG).show()
            }
        }
    }
}