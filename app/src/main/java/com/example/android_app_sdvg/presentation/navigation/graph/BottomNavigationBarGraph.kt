package com.example.android_app_sdvg.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_app_sdvg.presentation.handler.CalendarScreenHandler
import com.example.android_app_sdvg.presentation.handler.ClickerScreenHandler
import com.example.android_app_sdvg.presentation.handler.PersonalAccountScreenHandler
import com.example.android_app_sdvg.presentation.screen.CalendarScreen
import com.example.android_app_sdvg.presentation.screen.ClickerScreen
import com.example.android_app_sdvg.presentation.screen.PersonalAccountScreen
import com.example.android_app_sdvg.presentation.screen.Screen

/**
 * @author Lapoushko
 */

@Composable
fun BottomNavigationBarGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Calendar.route
    ) {
        composable(route = Screen.Calendar.route) {
            CalendarScreen(
                calendarScreenHandler = CalendarScreenHandler(
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