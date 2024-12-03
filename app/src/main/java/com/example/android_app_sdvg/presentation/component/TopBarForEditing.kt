package com.example.android_app_sdvg.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author Lapoushko
 */
@Composable
fun TopBarForEditing(
    onToBack: () -> Unit,
    save: () -> Unit,
    label: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onToBack()
        }) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = null)
        }

        Text(
            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
            text = label,
            fontSize = 20.sp,
        )

        IconButton(onClick = {
            save()
        }) {
            Icon(imageVector = Icons.Filled.Done, contentDescription = null)
        }
    }
}