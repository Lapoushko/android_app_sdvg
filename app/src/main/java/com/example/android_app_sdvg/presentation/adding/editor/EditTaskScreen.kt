package com.example.android_app_sdvg.presentation.adding.editor

import AddTaskScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_app_sdvg.presentation.model.task.TaskItem

/**
 * @author Lapoushko
 * экран редактирования
 */
@Composable
fun EditTaskScreen(
    handler: EditTaskScreenHandler,
    taskItem: TaskItem,
    viewModel: EditTaskScreenTaskScreenViewModel = hiltViewModel()
) {
    viewModel.updateTask(taskItem = taskItem)
    AddTaskScreen(
        dateStart = taskItem.dates.dateStart,
        handler = handler,
        viewModel = viewModel,
        label = "Изменить задачу"
    )
}