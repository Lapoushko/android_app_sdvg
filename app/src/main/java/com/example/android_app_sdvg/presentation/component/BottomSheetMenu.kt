@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.android_app_sdvg.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

/**
 * @author Lapoushko
 */
@Composable
fun BottomSheetMenu(
    onDismissRequest: () -> Unit,
    buttons: List<ChipButton>
){
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        buttons.forEach { button ->
            TextButton(onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        button.onClick()
                        onDismissRequest()
                    }
                }
            }) {
                Text(modifier = Modifier.fillMaxWidth(), text = button.text)
            }
        }
    }
}

class ChipButton(
    val text: String,
    val onClick: () -> Unit
)