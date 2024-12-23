package com.example.android_app_sdvg.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * @author Lapoushko
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerSwitchable(
    onConfirm: (TimePickerState) -> Unit,
    onCancel: () -> Unit,
    isTextInput: Boolean = false
) {
    val state = rememberTimePickerState(is24Hour = true)
    val formatter = remember { SimpleDateFormat("hh:mm", Locale.getDefault()) }
    val snackState = remember { SnackbarHostState() }
    val showingPicker = remember { mutableStateOf(!isTextInput) }
    val snackScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    TimePickerDialog(
        title = if (showingPicker.value && !isTextInput) {
            "Выбери время "
        } else {
            "Введи время "
        },
        onCancel = { onCancel() },
        onConfirm = {
            val cal = Calendar.getInstance()
            cal.set(Calendar.HOUR_OF_DAY, state.hour)
            cal.set(Calendar.MINUTE, state.minute)
            cal.isLenient = false
            snackScope.launch {
                snackState.showSnackbar("Введи время: ${formatter.format(cal.time)}")
            }
            onConfirm(
                state
            )
        },
        toggle = {
            if (configuration.screenHeightDp > 400) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .semantics {
                            @Suppress("DEPRECATION")
                            isContainer = true
                        }
                ) {
                    if (!isTextInput) {
                        IconButton(
                            modifier = Modifier
                                .size(64.dp, 72.dp)
                                .align(Alignment.BottomStart)
                                .zIndex(5f),
                            onClick = { showingPicker.value = !showingPicker.value }) {
                            val icon = if (showingPicker.value) {
                                Icons.Outlined.Keyboard
                            } else {
                                Icons.Outlined.Schedule
                            }
                            Icon(
                                icon,
                                contentDescription = if (showingPicker.value) {
                                    "Сменить на текстовый ввод"
                                } else {
                                    "Сменить на ввод из часов"
                                }
                            )
                        }
                    }
                }
            }
        }
    ) {
        if (showingPicker.value && configuration.screenHeightDp > 400 && !isTextInput) {
            TimePicker(state = state)
        } else {
            TimeInput(state = state)
        }
    }
}

@Composable
fun TimePickerDialog(
    title: String = "Выбери время",
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
        ) {
            toggle()
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = onCancel
                    ) { Text("Отменить") }
                    TextButton(
                        onClick = onConfirm
                    ) { Text("Сохранить") }
                }
            }
        }
    }
}