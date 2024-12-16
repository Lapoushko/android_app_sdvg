package com.example.android_app_sdvg.presentation.tasker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.presentation.component.CustomTopAppBar

/**
 * @author Lapoushko
 * Экран задач
 * @param taskerScreenHandler функции экрана
 * @param viewModel вью модель экрана
 */
@Composable
fun TaskerScreen(
    taskerScreenHandler: TaskerScreenHandler,
    viewModel: TaskerScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            CustomTopAppBar(stringResource(R.string.calendar))
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Row {
                IconButton(onClick = {
                    taskerScreenHandler.onToFilter()
                }) {
                    Icon(imageVector = Icons.Filled.FilterAlt, contentDescription = null)
                }

                IconButton(onClick = {
                    taskerScreenHandler.onToCreateTask()
                }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.tasks) { task ->
                    TaskerScreenListItem(
                        task = task,
                        onDelete = { viewModel.delete(task) },
                        onEdit = { taskerScreenHandler.onToEditTask(task) },
                        onComplete = { viewModel.completeTask(task) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskerScreenPreview() {
    TaskerScreen(
        taskerScreenHandler = TaskerScreenHandler(
            navController = rememberNavController()
        )
    )
}
