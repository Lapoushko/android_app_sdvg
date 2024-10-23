package com.example.android_app_sdvg.presentation.tasker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_app_sdvg.presentation.extension.toDateString
import com.example.android_app_sdvg.presentation.model.task.TaskItem

/**
 * @author Lapoushko
 */

@Composable
fun TaskerScreenListItem(task: TaskItem) {
    Card(
        modifier = Modifier.padding(10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = task.timer.toDateString())
                Text(text = task.timer.toDateString())
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(Alignment.CenterVertically),
                text = task.name,
                color = Color.Blue
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskerScreenListItemPreview() {
    TaskerScreenListItem(task = TaskItem())
}
