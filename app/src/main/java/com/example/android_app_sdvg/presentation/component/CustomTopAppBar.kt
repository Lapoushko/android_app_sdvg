@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.android_app_sdvg.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * @author Lapoushko
 */
@Composable
fun CustomTopAppBar(
    title: String
) {
    Column {
        TopAppBar(
            modifier = Modifier
                .padding(PaddingValues(top = 0.dp)),
            title = {
                Text(
                    text = title
                )
            }
        )
        UnderLine()
    }
}