package com.example.android_app_sdvg.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_app_sdvg.presentation.adding.abstracting.AbstractAddTaskScreenViewModel
import com.example.android_app_sdvg.presentation.extension.toTimeString

/**
 * @author Lapoushko
 */
@Composable
fun TimeField(
    label: String = "Время выполнения задачи",
    time: Int,
    onTimeClick: () -> Unit,
    viewModel: AbstractAddTaskScreenViewModel,
    isError: Boolean = false,
    error: String = "",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 70.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(20.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.clickable {
                onTimeClick()
                viewModel.toggleTimer()
            },
            placeholder = {
                Text(
                    text = label,
                    fontSize = 15.sp,
                )
            },
            enabled = false,
            value = if (time == 0) label else time.toTimeString(),
            onValueChange = {},
            isError = isError,
            supportingText = {
                if (isError) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = error,
                    )
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Timer,
                    contentDescription = "Таймер",
                )
            },
            singleLine = true
        )
    }
}