@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.android_app_sdvg.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @author Lapoushko
 */
@Composable
fun CustomTopAppBar(
    title: String
) {
    TopAppBar(modifier =
    Modifier
        .padding(PaddingValues(top = 0.dp))
        .shadow(3.dp, spotColor = Color.DarkGray),
        title = {
            Text(
                text = title
            )
        }
    )
}