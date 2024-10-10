@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.android_app_sdvg.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_app_sdvg.presentation.handler.AbstractScreenHandler
import com.example.android_app_sdvg.presentation.theme.Android_app_sdvgTheme

/**
 * @author Lapoushko
 *
 * Скрин главной активнити

 */
@Composable
fun CalendarScreen(
    calendarScreenHandler: AbstractScreenHandler
) {
    Android_app_sdvgTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxWidth(),
                topBar = {
                    TopAppBar(title = {
                        Text(
                            text = "Календарь"
                        )
                    })
                },
            ) { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(10.dp)
                        .fillMaxSize(),
                    state = rememberLazyListState(),
                    contentPadding = PaddingValues(top = 5.dp, bottom = 5.dp)
                ) {
                    items(
                        items = listOf(
                            1, 2
                        )
                    ) {
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    CalendarScreen(calendarScreenHandler = object : AbstractScreenHandler(){

    })
}

