package com.example.android_app_sdvg.presentation.adding.edit_task

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
    task: TaskItem,
    viewModel: EditTaskScreenTaskScreenViewModel = hiltViewModel()
) {
    AddTaskScreen(
        dateStart = task.dates.dateStart,
        handler = handler,
        viewModel = viewModel,
        label = "Изменить задачу"
    )
}