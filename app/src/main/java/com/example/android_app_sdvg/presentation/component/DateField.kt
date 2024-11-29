package com.example.android_app_sdvg.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_app_sdvg.presentation.adding.abstracting.AbstractAddTaskScreenViewModel
import com.example.android_app_sdvg.presentation.extension.toDateString

/**
 * @author Lapoushko
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(
    label: String,
    date: Long?,
    onDateClick: () -> Unit,
    viewModel: AbstractAddTaskScreenViewModel,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(20.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
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