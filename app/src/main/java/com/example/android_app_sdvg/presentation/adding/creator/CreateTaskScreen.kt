package com.example.android_app_sdvg.presentation.adding.creator

import AddTaskScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController

/**
 * @author Lapoushko
 * Создание задачи скрин
 *
 * @param dateStart день создания задачи
 * @param handler функции экрана
 * @param viewModel вью модель экрана
 */
@Composable
fun CreateTaskScreen(
    dateStart: Long,
    handler: CreateTaskScreenHandler,
    viewModel: CreateTaskScreenTaskScreenViewModel = hiltViewModel(),
) {
    AddTaskScreen(dateStart = dateStart, handler = handler, viewModel = viewModel)
}

@Preview(showBackground = true)
@Composable
fun CreateTaskScreenPreview() {
    CreateTaskScreen(
        dateStart = 0L,
        handler = CreateTaskScreenHandler(rememberNavController()),
    )
}
