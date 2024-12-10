package com.example.android_app_sdvg.presentation.tasker

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.ModeEdit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_app_sdvg.domain.entity.task.TaskStatus
import com.example.android_app_sdvg.domain.entity.task.getTaskStatus
import com.example.android_app_sdvg.presentation.extension.toDateString
import com.example.android_app_sdvg.presentation.model.task.DatesItem
import com.example.android_app_sdvg.presentation.model.task.TaskItem

/**
 * @author Lapoushko
 */

@Composable
fun TaskerScreenListItem(
    task: TaskItem,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onComplete: () -> Unit,
) {
    var expandedState by remember {
        mutableStateOf(false)
    }

    val rotationScale by animateFloatAsState(targetValue = if (expandedState) 180f else 0f,
        label = ""
    )
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(10.dp)
            .animateContentSize(),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            expandedState = !expandedState
        },
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
                color = if (task.taskStatus.getTaskStatus() == TaskStatus.IN_PROGRESS) Color.Green else Color.Red
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
                RowIconButton(
                    onClick = { onDelete() },
                    imageVector = Icons.Outlined.DeleteOutline
                )

                RowIconButton(
                    onClick = {
                        onEdit() },
                    imageVector = Icons.Outlined.ModeEdit
                )

                RowIconButton(
                    onClick = {
                        if (task.taskStatus.getTaskStatus() == TaskStatus.IN_PROGRESS) {
                            onComplete()
                            Toast.makeText(context, "Задача выполнена", Toast.LENGTH_SHORT).show()
                        }
                    },
                    imageVector = Icons.Outlined.Done
                )
            }
        }
    }
}

@Composable
private fun RowIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector
){
    IconButton(onClick = { onClick() }) {
        Icon(imageVector = imageVector, contentDescription = null)
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row {
        Text(
            text = label,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .padding(horizontal = 10.dp)

        )
        Text(
            text = value,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskerScreenListItemPreview() {
    val task = TaskItem(
        0,
        "",
        "", DatesItem(0, 0),
        "",
        "",
        "",
        "",
        "",
        ""
    )
    TaskerScreenListItem(
        task = task,
        onDelete = {  },
        onEdit = { },
        onComplete = {}
    )
}
