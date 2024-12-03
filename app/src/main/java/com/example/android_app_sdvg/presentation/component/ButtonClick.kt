package com.example.android_app_sdvg.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.android_app_sdvg.presentation.theme.ButtonsColor

/**
 * @author Lapoushko
 */
@Composable
fun ButtonClick(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonsColor,
            contentColor = Color.White
        )
    ) {
        Text(
            fontSize = 26.sp,
            text = text
        )
    }
}