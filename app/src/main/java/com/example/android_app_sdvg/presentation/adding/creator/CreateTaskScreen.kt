package com.example.android_app_sdvg.presentation.adding.creator

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.presentation.adding.abstracting.AddTaskScreen

/**
 * @author Lapoushko
 * Создание задачи скрин
 *
 * @param handler функции экрана
 * @param viewModel вью модель экрана
 */
@Composable
fun CreateTaskScreen(
    handler: CreateTaskScreenHandler,
    viewModel: CreateTaskScreenTaskScreenViewModel = hiltViewModel(),
) {
    AddTaskScreen(handler = handler, viewModel = viewModel)
}

@Preview(showBackground = true)
@Composable
fun CreateTaskScreenPreview() {
    CreateTaskScreen(
        handler = CreateTaskScreenHandler(rememberNavController()),
    )
}
