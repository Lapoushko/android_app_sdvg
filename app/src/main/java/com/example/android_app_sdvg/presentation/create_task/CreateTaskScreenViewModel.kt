package com.example.android_app_sdvg.presentation.create_task

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android_app_sdvg.domain.entity.category.Category
import com.example.android_app_sdvg.domain.entity.prioriry.Priority
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Lapoushko
 * Вью модель для экрана создания зхадачи
 */
@HiltViewModel
class CreateTaskScreenViewModel @Inject constructor() : ViewModel() {
    var name: String by mutableStateOf("")
    var desc: String by mutableStateOf("")
    var time: String by mutableStateOf("0")
    var priority by mutableStateOf<Priority?>(null)
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
        if (name.isNotEmpty() || desc.isNotEmpty()){
            val task = TaskItem(
                name = name,
                description = desc,
                dateStart = dateStart,
                timer = time.toLong(),
                capacity = 0L,
                periodicity = periodicity.toIntOrNull() ?: 0,
                priorityItem = priority ?: Priority.HIGH,
                categoryItem = Category.STANDART
            )

            onToBack()
            Log.d(Constants.LOG_KEY, task.toString())
        } else{
            Log.d(Constants.LOG_KEY, "Заполните полностью данные")
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

//    fun updatePriority(value: String) {
//        priority = value.getEnum<PriorityItem>()
//    }

    fun updatePeriodicity(value: String) {
        periodicity = value
    }
}