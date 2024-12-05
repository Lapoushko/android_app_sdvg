import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Pin
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.domain.entity.category.Category
import com.example.android_app_sdvg.domain.entity.prioriry.Priority
import com.example.android_app_sdvg.presentation.adding.abstracting.AbstractAddTaskScreenHandler
import com.example.android_app_sdvg.presentation.adding.abstracting.AbstractAddTaskScreenViewModel
import com.example.android_app_sdvg.presentation.adding.editor.EditTaskScreenHandler
import com.example.android_app_sdvg.presentation.component.CustomTopAppBar
import com.example.android_app_sdvg.presentation.component.DateField
import com.example.android_app_sdvg.presentation.component.DropdownMenuBox
import com.example.android_app_sdvg.presentation.component.TextFieldOption
import com.example.android_app_sdvg.presentation.component.TimeField
import com.example.android_app_sdvg.presentation.component.TimePickerSwitchable
import com.example.android_app_sdvg.presentation.component.TopBarForEditing
import com.example.android_app_sdvg.presentation.extension.toDateString
import com.example.android_app_sdvg.presentation.extension.toIntTime

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
fun AddTaskScreen(
    dateStart: Long,
    handler: AbstractAddTaskScreenHandler,
    viewModel: AbstractAddTaskScreenViewModel,
    label: String = "Новая задача"
) {
    val taskState = viewModel.taskState

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CustomTopAppBar(dateStart.toDateString())
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())

        ) {
            TopBarForEditing(
                onToBack = {handler.onToBack() },
                save = {viewModel.saveTask { handler.onToBack() }},
                label = label,
            )

            TextFieldOption(
                text = taskState.name.text,
                onTextChange = {
                    viewModel.updateName(it)
                },
                label = stringResource(id = R.string.create_task_name),
                isError = !taskState.name.error?.naming.isNullOrEmpty(),
                error = taskState.name.error?.naming ?: ""

            )
            TextFieldOption(
                text = taskState.desc.text,
                onTextChange = {
                    viewModel.updateDesc(it)
                },
                label = stringResource(id = R.string.create_task_desc),
                isError = !taskState.desc.error?.naming.isNullOrEmpty(),
                error = taskState.desc.error?.naming ?: ""

            )

            DropdownMenuBox(
                items = Category.entries.map { it.naming },
                label = "Категория",
                onTextChange = { viewModel.updateCategory(it) }
                ,
                isError = !taskState.category.error?.naming.isNullOrEmpty(),
                error = taskState.category.error?.naming ?: "")
            DropdownMenuBox(
                items = Priority.entries.map { it.naming },
                label = "Приоритет",
                onTextChange = { viewModel.updatePriority(it) },
                isError = !taskState.priority.error?.naming.isNullOrEmpty(),
                error = taskState.priority.error?.naming ?: ""
            )

            TextFieldOption(
                text = taskState.periodicity.text,
                onTextChange = {
                    viewModel.updatePeriodicity(it)
                },
                imageVector = Icons.Outlined.Pin,
                keyboardType = KeyboardType.Decimal,
                label = stringResource(id = R.string.create_task_periodicity),
                isError = !taskState.periodicity.error?.naming.isNullOrEmpty(),
                error = taskState.periodicity.error?.naming ?: ""
            )

            DateField(
                label = "Дата начала задачи",
                date = taskState.dateStart,
                onDateClick = { },
                viewModel = viewModel
            )

            DateField(
                label = "Дата завершения задачи",
                date = taskState.dateEnd,
                onDateClick = {},
                viewModel = viewModel
            )

            TimeField(
                label = "Время выполнения задачи",
                time = taskState.capacity.toIntTime(),
                onTimeClick = {},
                viewModel = viewModel,
            )
        }
    }

    if (taskState.showModal) {
        DateRangePickerModal(
            onDateRangeSelected = {
                viewModel.updateDateStart(it.first ?: 0L)
                viewModel.updateDateEnd(it.second ?: 0L)
                viewModel.toggleCalendar()
            },
            onDismiss = { viewModel.toggleCalendar() }
        )
    }

    if (taskState.showTimePicker) {
        TimePickerSwitchable(
            onConfirm = {
                viewModel.updateTimePickerState(it.hour, it.minute)
                viewModel.toggleTimer()
            },
            onCancel = {
                viewModel.toggleTimer()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    val dateRangePickerState = rememberDateRangePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                }
            ) {
                Text(stringResource(R.string.ok_in_calendar))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.close_calendar))
            }
        }
    ) {
        DateRangePicker(
            state = dateRangePickerState,
            title = {
                Text(
                    text = "Выбери диапозон"
                )
            },
            showModeToggle = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreen(
        dateStart = 0L,
        handler = EditTaskScreenHandler(rememberNavController()),
        viewModel = hiltViewModel()
    )
}
