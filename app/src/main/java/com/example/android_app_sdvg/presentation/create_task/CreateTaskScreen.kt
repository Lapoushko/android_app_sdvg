package com.example.android_app_sdvg.presentation.create_task

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material.icons.outlined.Pin
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.domain.entity.category.Category
import com.example.android_app_sdvg.domain.entity.prioriry.Priority
import com.example.android_app_sdvg.presentation.extension.toDateString
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.presentation.tasker.DatePickerModal

/**
 * @author Lapoushko
 * Создание задачи скрин
 *
 * @param dateStart день создания задачи
 * @param handler функции экрана
 * @param viewModel вью модель экрана
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    dateStart: Long,
    handler: CreateTaskScreenHandler,
    viewModel: CreateTaskScreenViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf(viewModel.name) }
    var desc by remember { mutableStateOf(viewModel.desc) }
    var capacity by remember { mutableStateOf(viewModel.capacity) }
    var periodicity by remember { mutableStateOf(viewModel.periodicity) }

    val showModal = viewModel.showModal
    val selectedDateStart = viewModel.dateStart.collectAsState().value
    val selectedDateEnd = viewModel.dateEnd.collectAsState().value

    var curDate by remember { mutableStateOf(CurrentDate.START) }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {
            Text(text = dateStart.toDateString())
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    handler.onToBack()
                }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                }

                Text(
                    modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
                    text = "Новая задача",
                    fontSize = 20.sp,
                )

                IconButton(onClick = {
                    viewModel.saveTask { handler.onToBack() }
                }) {
                    Icon(imageVector = Icons.Filled.Done, contentDescription = null)
                }
            }

            TextFieldOption(
                text = name,
                onTextChange = {
                    name = it
                    viewModel.updateName(it)
                },
                label = stringResource(id = R.string.create_task_name)
            )
            TextFieldOption(
                text = desc,
                onTextChange = {
                    desc = it
                    viewModel.updateDesc(it)
                }, label = stringResource(id = R.string.create_task_desc)
            )

            DropdownMenuBox(
                items = Category.entries.map { it.naming },
                label = "Категория",
                onTextChange = { viewModel.updateCategory(it) })
            DropdownMenuBox(
                items = Priority.entries.map { it.naming },
                label = "Приоритет",
                onTextChange = { viewModel.updatePriority(it) }
            )

            TextFieldOption(
                text = periodicity,
                onTextChange = {
                    periodicity = it
                    viewModel.updatePeriodicity(it)
                },
                imageVector = Icons.Outlined.Pin,
                keyboardType = KeyboardType.Decimal,
                label = stringResource(id = R.string.create_task_periodicity)
            )

            DateField(
                label = "Дата начала задачи",
                date = selectedDateStart,
                onDateClick = { curDate = CurrentDate.START },
                viewModel = viewModel
            )

            DateField(
                label = "Дата завершения задачи",
                date = selectedDateEnd,
                onDateClick = { curDate = CurrentDate.END },
                viewModel = viewModel
            )
        }
    }

    if (showModal) {
        DatePickerModal(
            onDateSelected = {
                if (curDate == CurrentDate.START){
                    viewModel.updateDateStart(it ?: 0L)
                } else{
                    viewModel.updateDateEnd(it ?: 0L)
                }
                viewModel.toggleCalendar()
            },
            onDismiss = { viewModel.toggleCalendar() }
        )
    }
}

@Composable
fun TextFieldOption(
    text: String,
    onTextChange: (String) -> Unit,
    imageVector: ImageVector = Icons.Outlined.Keyboard,
    keyboardType: KeyboardType = KeyboardType.Text,
    label: String,
) {
    TextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 10.dp)
        .clip(RoundedCornerShape(20.dp)),
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        placeholder = {
            Text(
                text = label, fontSize = 15.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        leadingIcon = {
            Icon(
                imageVector = imageVector, contentDescription = null
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBox(
    items: List<String>,
    label: String,
    onTextChange: (String) -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf("")
    }

    Box {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .menuAnchor(),
                value = selectedItem,
                readOnly = true,
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                onValueChange = {
                },
                placeholder = {
                    Text(
                        text = label, fontSize = 15.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Edit, contentDescription = null
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedItem = item
                            onTextChange(selectedItem)
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateField(
    label: String,
    date: Long?,
    onDateClick: () -> Unit,
    viewModel: CreateTaskScreenViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(20.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(IntrinsicSize.Max)
        )

        Spacer(modifier = Modifier.width(8.dp))

        TextField(
            modifier = Modifier.clickable {
                onDateClick()
                viewModel.toggleCalendar()
            },
            placeholder = {
                Text(
                    text = "Выберите дату",
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            enabled = false,
            value = if (date == 0L) "" else date?.toDateString() ?: "",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = "Календарь",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            singleLine = true
        )
    }
}

@Composabe
fun CustomTimePicker(){
    TimePickerDialog(
        title =
        if (showingPicker.value) {
            "Select Time "
        } else {
            "Enter Time"
        },
        onCancel = { showTimePicker = false },
        onConfirm = {
            val cal = Calendar.getInstance()
            cal.set(Calendar.HOUR_OF_DAY, state.hour)
            cal.set(Calendar.MINUTE, state.minute)
            cal.isLenient = false
            snackScope.launch {
                snackState.showSnackbar("Entered time: ${formatter.format(cal.time)}")
            }
            showTimePicker = false
        },
        toggle = {
            if (configuration.screenHeightDp > 400) {
                IconButton(onClick = { showingPicker.value = !showingPicker.value }) {
                    val icon =
                        if (showingPicker.value) {
                            Icons.Outlined.Keyboard
                        } else {
                            Icons.Outlined.Schedule
                        }
                    Icon(
                        icon,
                        contentDescription =
                        if (showingPicker.value) {
                            "Switch to Text Input"
                        } else {
                            "Switch to Touch Input"
                        }
                    )
                }
            }
        }
    ) {
        if (showingPicker.value && configuration.screenHeightDp > 400) {
            TimePicker(state = state)
        } else {
            TimeInput(state = state)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateTaskScreenPreview() {
    CreateTaskScreen(
        dateStart = 0L,
        handler = CreateTaskScreenHandler(rememberNavController()),
    )
}

private enum class CurrentDate{
    START,
    END
}