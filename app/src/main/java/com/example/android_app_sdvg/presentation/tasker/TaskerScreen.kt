package com.example.android_app_sdvg.presentation.tasker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.presentation.extension.toDateString

/**
 * @author Lapoushko
 * Экран задач
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskerScreen(
    taskerScreenHandler: TaskerScreenHandler,
    viewModel: TaskerScreenViewModel = viewModel()
) {
    val showModal = viewModel.showModal
    val selectedDate = viewModel.selectedDate

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.calendar)) }
            )
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

                IconButton(onClick = { taskerScreenHandler.onToCreateTask(selectedDate!!) }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }


            Text(
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                text = selectedDate?.toDateString() ?: "Дата не выбрана"
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(10) {
                    Text(text = "task")
                }
            }
        }
    }

    if (showModal) {
        DatePickerModal(
            onDateSelected = {
                viewModel.selectDate(it ?: 0L)
                viewModel.toggleCalendar()
            },
            onDismiss = { viewModel.toggleCalendar() })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
            }) {
                Text(stringResource(R.string.ok_in_calendar))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.close_calendar))
            }
        }
    ) {
        DatePicker(state = datePickerState)
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
