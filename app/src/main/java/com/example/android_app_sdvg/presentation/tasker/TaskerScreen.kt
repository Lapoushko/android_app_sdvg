package com.example.android_app_sdvg.presentation.tasker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.presentation.component.CustomTopAppBar
import com.example.android_app_sdvg.presentation.component.DateRangePickerModal
import com.example.android_app_sdvg.presentation.extension.toDateString

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
                OutlinedButton(
                    onClick = {
                        viewModel.toggleCalendar()
                    },
                    modifier = Modifier
                        .padding(PaddingValues(horizontal = 30.dp))
                        .fillMaxWidth(0.8f)
                ) {
                    Text(text = stringResource(R.string.button_open_calendar))
                }

                IconButton(onClick = {
                    taskerScreenHandler.onToCreateTask(
                        state.selectedDates?.dateStart ?: 0L
                    )
                }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }


            Text(
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                text =
                "${state.selectedDates?.dateStart?.toDateString() ?: ""} - ${state.selectedDates?.dateEnd?.toDateString() ?: ""}"
            )

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

    if (state.showModal) {
        DateRangePickerModal(
            onDateRangeSelected = {
                viewModel.selectDate(Pair(first = it.first, second = it.second))
                viewModel.toggleCalendar()
            },
            onDismiss = { viewModel.toggleCalendar() })
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
