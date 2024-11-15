package com.example.android_app_sdvg.presentation.tasker

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.ModeEdit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_app_sdvg.presentation.extension.toDateString
import com.example.android_app_sdvg.presentation.model.task.DatesItem
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.presentation.theme.Shapes

/**
 * @author Lapoushko
 */

@Composable
fun TaskerScreenListItem(
    task: TaskItem,
    viewModel: TaskerScreenViewModel = hiltViewModel()
) {
    var expandedState by remember {
        mutableStateOf(false)
    }

    val rotationScale by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)

    Card(
        modifier = Modifier
            .padding(10.dp)
            .animateContentSize(),
        shape = Shapes.small,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = task.dates.dateStart.toDateString())
                Text(text = task.dates.dateEnd.toDateString())
            }
            Text(
                modifier = Modifier
                    .weight(6f)
                    .align(Alignment.CenterVertically),
                text = task.name,
                color = Color.Blue
            )
            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .rotate(rotationScale),
                onClick = {
                    expandedState = !expandedState
                }) {
                Icon(imageVector = Icons.Outlined.ExpandMore, contentDescription = null)
            }
        }
        if (expandedState) {
            Row {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    DetailRow("Приоритет", task.priorityItem)
                    DetailRow("Категория", task.categoryItem)
                }
                IconButton(onClick = { viewModel.delete(task) }) {
                    Icon(imageVector = Icons.Outlined.DeleteOutline, contentDescription = null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.ModeEdit, contentDescription = null)
                }
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
//        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .padding(horizontal = 10.dp)

        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Blue
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskerScreenListItemPreview() {
    TaskerScreenListItem(task = TaskItem("", "", DatesItem(0,0), "", "", "", "", ""))
}
