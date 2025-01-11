package com.example.android_app_sdvg.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_app_sdvg.presentation.extension.CountryDateFormat
import com.example.android_app_sdvg.presentation.extension.TimeDay
import com.example.android_app_sdvg.presentation.extension.toDateString

/**
 * @author Lapoushko
 */
@Composable
fun DateField(
    label: String,
    date: Long?,
    onDateClick: () -> Unit,
    modifier: Modifier = Modifier,
    timeDay: TimeDay = TimeDay.START
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onDateClick()
                },
            placeholder = {
                Text(
                    text = label,
                    fontSize = 12.sp,
                )
            },
            enabled = false,
            value = if (date == 0L) label else date?.toDateString(timeDay = timeDay, countryDateFormat = CountryDateFormat.US) ?: "",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = "Календарь",
                )
            },
            singleLine = true,
            label = { Text(text = label, fontSize = 12.sp) }
        )
    }
}