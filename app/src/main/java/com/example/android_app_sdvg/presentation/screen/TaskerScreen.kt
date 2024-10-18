package com.example.android_app_sdvg.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.presentation.handler.TaskerScreenHandler
import com.example.android_app_sdvg.presentation.screen.extension.toDate
import java.time.LocalDate
import java.util.Calendar

/**
 * @author Lapoushko
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskerScreen(
    taskerScreenHandler: TaskerScreenHandler
) {
    var showModal by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<Long?>(Calendar.getInstance().timeInMillis)}

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.calendar)) }
            )
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            OutlinedButton(
                onClick = {
                    showModal = taskerScreenHandler
                        .openCalendar(showModal)

                },
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.button_open_calendar))
            }

            Text(
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                text = selectedDate?.toDate() ?: "Дата не выбрана"
            )
        }
    }

    if (showModal) {
        DatePickerModal(
            onDateSelected = {
                selectedDate = it
                taskerScreenHandler.openCalendar(showModal)
            },
            onDismiss = { showModal = taskerScreenHandler.openCalendar(showModal) })
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
                onDismiss()
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
