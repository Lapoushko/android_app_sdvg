package com.example.android_app_sdvg.presentation.tasker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.domain.entity.task.TaskStatus
import com.example.android_app_sdvg.presentation.component.CustomTopAppBar
import com.example.android_app_sdvg.presentation.component.DateRangePickerModal
import com.example.android_app_sdvg.presentation.component.chip.CustomAssistChip

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
    val chipsState = viewModel.chipsState.collectAsState()
    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            CustomTopAppBar(stringResource(R.string.calendar))
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Row {
                IconButton(onClick = {
                    taskerScreenHandler.onToCreateTask()
                }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }

            LazyRow(modifier = Modifier.padding(10.dp)) {
                items(chipsState.value) { chip ->
                    CustomAssistChip(chip)
                }
            }

            Column (
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            ) {
                state.tasksWithDates.forEach { (date, tasks) ->
                    Column {
                        if (tasks.isNotEmpty()) {
                            Text(modifier = Modifier.padding(horizontal = 20.dp), text = date.uppercase())
                            tasks.forEach { task ->
                                TaskerScreenListItem(
                                    task = task,
                                    onDelete = { viewModel.delete(task) },
                                    onEdit = { taskerScreenHandler.onToEditTask(task) },
                                    onComplete = { viewModel.setStatus(task, status = TaskStatus.COMPLETED) },
                                    onCancel = {viewModel.setStatus(task, status = TaskStatus.IN_PROGRESS)}
                                )
                            }
                        }
                    }
                }
            }
        }

        if (state.isNeedToShowCalendar) {
            DateRangePickerModal(
                onDateRangeSelected = {
                    viewModel.filterByDate(Pair(first = it.first, second = it.second))
                    viewModel.toggleCalendar()
                },
                onDismiss = { viewModel.toggleCalendar() }
            )
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
