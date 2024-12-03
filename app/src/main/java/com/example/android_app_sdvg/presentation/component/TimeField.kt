package com.example.android_app_sdvg.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.ExperimentalMaterial3Api
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeField(
    label: String = "Время выполнения задачи",
    time: Int,
    onTimeClick: () -> Unit,
    viewModel: AbstractAddTaskScreenViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(20.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.width(IntrinsicSize.Max)
        )

        Spacer(modifier = Modifier.width(8.dp))

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
            value = time.toTimeString(),
            onValueChange = {},
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