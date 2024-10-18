package com.example.android_app_sdvg.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_app_sdvg.presentation.handler.TaskerScreenHandler
import com.example.android_app_sdvg.presentation.handler.ClickerScreenHandler
import com.example.android_app_sdvg.presentation.handler.PersonalAccountScreenHandler
import com.example.android_app_sdvg.presentation.screen.ClickerScreen
import com.example.android_app_sdvg.presentation.screen.PersonalAccountScreen
import com.example.android_app_sdvg.presentation.screen.Screen
import com.example.android_app_sdvg.presentation.screen.TaskerScreen

/**
 * @author Lapoushko
 * Граф навигации нижнего бара
 * @param navController контроллер навигации
 */

@Composable
fun BottomNavigationBarGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Tasker.route
    ) {
        composable(route = Screen.Tasker.route) {
            TaskerScreen(
                taskerScreenHandler = TaskerScreenHandler(
                    navController = navController
                )
            )
        }
        composable(route = Screen.Clicker.route) {
            ClickerScreen(
                clickerScreenHandler = ClickerScreenHandler(
                    navController = navController
                )
            )
        }
        composable(route = Screen.PersonalAccount.route) {
            PersonalAccountScreen(
                personalAccountScreenHandler = PersonalAccountScreenHandler(
                    navController = navController
                )
            )
        }
    }
}