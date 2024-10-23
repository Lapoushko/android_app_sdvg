package com.example.android_app_sdvg.presentation.create_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.presentation.extension.toDateString

/**
 * @author Lapoushko
 * Создание задачи скрин
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    dateStart: Long,
    handler: CreateTaskScreenHandler,
    viewModel: CreateTaskScreenViewModel = viewModel()
) {
    var name = viewModel.name
    var desc = viewModel.desc
    var time = viewModel.time
    var priority = viewModel.priority
    var periodicity = viewModel.periodicity

    viewModel.dateStart = dateStart

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = dateStart.toDateString())
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        handler.onToBack()
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                }

                Text(
                    modifier = Modifier
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    text = "Новая задача",
                    fontSize = 20.sp,
                )

                IconButton(
                    onClick = {
                        viewModel.saveTask { handler.onToBack() }
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Done, contentDescription = null)
                }
            }

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(20.dp)),
                value = name,
                onValueChange = {
                    name = it
                    viewModel.updateName(name)
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.create_task_name),
                        fontSize = 15.sp
                    )
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(20.dp)),
                value = desc,
                onValueChange = {
                    desc = it
                    viewModel.updateDesc(desc)
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.create_task_desc),
                        fontSize = 15.sp
                    )
                },
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(20.dp)),
                value = time,
                onValueChange = {
                    viewModel.updateTime(time)
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.create_task_time),
                        fontSize = 15.sp
                    )
                },
            )
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp, vertical = 10.dp)
//                    .clip(RoundedCornerShape(20.dp)),
//                value = priority,
//                onValueChange = {
//                    priority = it
//                    viewModel.updatePriority(priority?.name ?: "")
//                },
//                placeholder = {
//                    Text(
//                        text = stringResource(R.string.create_task_priority),
//                        fontSize = 15.sp
//                    )
//                },
//            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(20.dp)),
                value = periodicity,
                onValueChange = {
                    periodicity = it
                    viewModel.updatePeriodicity(periodicity)
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.create_task_periodicity),
                        fontSize = 15.sp
                    )
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateTaskScreenPreview() {
    CreateTaskScreen(dateStart = 0L, handler = CreateTaskScreenHandler(rememberNavController()))
}
