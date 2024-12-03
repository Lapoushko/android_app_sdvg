package com.example.android_app_sdvg.presentation.extension

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.android_app_sdvg.presentation.navigation.screen.ScreenBar

/**
 * @author Lapoushko
 *
 * Экстеншен функция для бара навигации
 * @param screenBar скрин
 * @param destination куда попасть
 * @param navController контроллер навигации
 */
@Composable
fun RowScope.AddItem(
    screenBar: ScreenBar,
    destination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        selected = destination?.hierarchy?.any {
            it.route == screenBar.route
        } == true,
        onClick = {
            navController.navigate(screenBar.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = {
            val icon = if (destination?.hierarchy?.any { it.route == screenBar.route } == true) {
                screenBar.setIcon
            } else {
                screenBar.unsetIcon
            }

            Icon(
                imageVector = icon ?: Icons.Filled.Close,
                contentDescription = screenBar.title
            )
        },
        label = {
            Text(
                text = screenBar.title,
            )
        },
//        alwaysShowLabel = false
    )
}