@file:OptIn(ExperimentalLayoutApi::class)

package com.example.android_app_sdvg.presentation.tasker.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Light
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.domain.entity.prioriry.Priority
import com.example.android_app_sdvg.presentation.component.CustomTopAppBar
import com.example.android_app_sdvg.presentation.component.DateRangePickerModal
import com.example.android_app_sdvg.presentation.component.TopBarForEditing
import com.example.android_app_sdvg.presentation.component.chip.CustomFilterChip

/**
 * @author Lapoushko
 * экран фильтрации
 */
@Composable
fun FilterScreen(
    viewModel: FilterScreenViewModel = hiltViewModel(),
    onToBack: () -> Unit
) {
    val state = viewModel.state
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar("Фильтрация")
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TopBarForEditing(
                onToBack = { onToBack() },
                save = { viewModel.saveFilter { onToBack() } },
                label = "",
            )
            Filter(
                label = "Фильтрация по приоритетам",
                values = Priority.entries.map { it.naming },
                add = { viewModel.addFilter(it) },
                remove = { viewModel.removeFilter(it) }
            )
            Calendar { viewModel.toggleCalendar() }
        }
        if (state.showModal) {
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

@Composable
private fun Filter(
    label: String,
    values: List<String>,
    add: (String) -> Unit,
    remove: (String) -> Unit
) {
    Text(text = label)
    FlowRow(maxItemsInEachRow = 3) {
        values.forEach {
            CustomFilterChip(
                text = it,
                onAdd = { add(it) },
                onDelete = { remove(it) },
                imageVector = Icons.Filled.Light
            )
        }
    }
}

@Composable
private fun Calendar(
    onClickCalendar: () -> Unit
){
    OutlinedButton(
        onClick = {
            onClickCalendar()
        },
        modifier = Modifier
            .padding(PaddingValues(horizontal = 30.dp))
            .fillMaxWidth(0.8f)
    ) {
        Text(text = stringResource(R.string.button_open_calendar))
    }
}

@Preview(showBackground = true)
@Composable
fun FilterScreenPreview() {
    FilterScreen(onToBack = {})
}