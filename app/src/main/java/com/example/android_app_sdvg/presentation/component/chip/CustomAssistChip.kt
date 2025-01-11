package com.example.android_app_sdvg.presentation.component.chip

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * @author Lapoushko
 */

@Composable
fun CustomAssistChip(
    chipActions: ChipActions
){
    AssistChip(
        onClick = { chipActions.onClick() },
        label = { Text(chipActions.label) },
        leadingIcon = {
            if (chipActions.isAdded) {
                Icon(
                    modifier = Modifier.size(AssistChipDefaults.IconSize),
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Localized description",
                )
            }
        },
        trailingIcon = {
            if (!chipActions.isAdded) {
                Icon(
                    modifier = Modifier.size(AssistChipDefaults.IconSize),
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Localized description",
                )
            }
        },
        shape = RoundedCornerShape(40.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun CustomAssistChipPreview(){
    CustomAssistChip(chipActions = ChipActions(label = "example", onClick = {}))
}

class ChipActions(
    val label: String,
    val onClick: () -> Unit,
    val isAdded: Boolean = false
)