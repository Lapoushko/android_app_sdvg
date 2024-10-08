package com.example.android_app_sdvg

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.presentation.handler.MainScreenHandlerImpl
import com.example.android_app_sdvg.presentation.screen.MainScreen
import com.example.android_app_sdvg.presentation.screen.Screen
import dagger.hilt.android.HiltAndroidApp

/**
 * Настройки приложения
 *
 * @author Lapoushko
 */
@HiltAndroidApp
class SDVGApplication : Application() {
    @Composable
    fun SDVGNavHost() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.Main.route
        ) {
            composable(route = Screen.Main.route) {
                MainScreen(
                    MainScreenHandlerImpl()
                )
            }
        }
    }
}