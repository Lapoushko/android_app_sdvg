package com.example.android_app_sdvg.presentation.mapper

import androidx.core.text.isDigitsOnly
import com.example.android_app_sdvg.domain.entity.category.Category
import com.example.android_app_sdvg.domain.entity.category.getCategory
import com.example.android_app_sdvg.domain.entity.prioriry.Priority
import com.example.android_app_sdvg.domain.entity.prioriry.getPriority
import com.example.android_app_sdvg.domain.entity.task.Task
import com.example.android_app_sdvg.presentation.extension.toIntTime
import com.example.android_app_sdvg.presentation.extension.toLongDate
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.presentation.model.task.toDate
import javax.inject.Inject

/**
 * @author Lapoushko
 * маппер из ui в task
 */
interface TaskUiToTaskMapper {
    /**
     * перевод
     * @param taskUi задача из ui
     * @return задача из domain
     */
    operator fun invoke(taskUi: TaskItem) : Task
}

class TaskUiToTaskMapperImpl @Inject constructor(): TaskUiToTaskMapper{
    /**
     * перевод
     * @param taskUi задача из ui
     * @return задача из domain
     */
    override fun invoke(taskUi: TaskItem): Task {
        return Task(
            name = taskUi.name,
            description = taskUi.description,
            dates = taskUi.dates.toDate(),
            timer = if (taskUi.timer.isEmpty()) 0 else taskUi.timer.toLong(),
            capacity = taskUi.capacity.toIntTime(),
            periodicity = taskUi.periodicity.toIntOrNull() ?: 0,
            priority = taskUi.priorityItem.getPriority() ?: Priority.HIGH,
            category = taskUi.categoryItem.getCategory() ?: Category.STANDARD
        )
    }
}
