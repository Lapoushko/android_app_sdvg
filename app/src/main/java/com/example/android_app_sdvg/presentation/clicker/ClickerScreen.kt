package com.example.android_app_sdvg.presentation.clicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.presentation.component.ButtonClick
import com.example.android_app_sdvg.presentation.component.CustomTopAppBar

/**
 * @author Lapoushko
 *
 * Экран кликера
 */
@Composable
fun ClickerScreen(
    viewModel: ClickerScreenViewModel = hiltViewModel()
) {
    val clicks = viewModel.countClick.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(stringResource(R.string.clicker))
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = clicks.value.toString(),
                fontSize = 64.sp
            )
            ClickableImage { viewModel.click() }
            ButtonClick(
                text = "Обнулить"
            ) {
                viewModel.clearClicks()
            }
        }
    }
}

@Composable
private fun ClickableImage(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(

            ),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .size(256.dp)
                .clip(CircleShape),
        ) {
            Text(
                fontSize = 64.sp,
                text = "Клик"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClickerScreenPreview() {
    ClickerScreen()
}