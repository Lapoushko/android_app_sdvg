package com.example.android_app_sdvg.presentation.clicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.presentation.component.CustomTopAppBar

/**
 * @author Lapoushko
 *
 * Экран кликера
 *@param clickerScreenHandler методы кликера
 */
@Composable
fun ClickerScreen(
    clickerScreenHandler: ClickerScreenHandler,
    viewModel: ClickerScreenViewModel = hiltViewModel()
) {
    val clicks = viewModel.countClick.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CustomTopAppBar(stringResource(R.string.clicker))
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Button(onClick = { viewModel.clearClicks() }) {
                Text(text = "Очистить клики")
            }
            Text(
                text = clicks.value.toString()
            )
            Button(onClick = { viewModel.click() }) {
                Text(text = "Кликни")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClickerScreenPreview() {
    ClickerScreen(
        clickerScreenHandler = ClickerScreenHandler(
            navController = rememberNavController()
        ),
        viewModel = hiltViewModel()
    )
}