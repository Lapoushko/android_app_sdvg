package com.example.android_app_sdvg.presentation.tasker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.presentation.component.BottomSheetMenu
import com.example.android_app_sdvg.presentation.component.ChipButton
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
                items(viewModel.chips) { chip ->
                    CustomAssistChip(chip)
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
        if (state.isNeedToShowBottomSheet) {
            BottomSheetMenu(
                onDismissRequest = { viewModel.toggleBottomSheet() },
                buttons = listOf(
                    ChipButton(
                        text = TaskerScreenViewModel.PRIORITY_KEY,
                        onClick = { viewModel.sortByQuery(TaskerScreenViewModel.PRIORITY_KEY) }
                    ),
                    ChipButton(
                        text = TaskerScreenViewModel.CATEGORY_KEY,
                        onClick = { viewModel.sortByQuery(TaskerScreenViewModel.CATEGORY_KEY) }
                    )
                )
            )
        }

        if (state.isNeedToShowCalendar) {
            DateRangePickerModal(
                onDateRangeSelected = {
                    viewModel.selectDate(Pair(first = it.first, second = it.second))
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
