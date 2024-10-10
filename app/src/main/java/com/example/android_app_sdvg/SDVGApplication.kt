package com.example.android_app_sdvg

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.presentation.screen.BottomBarScreen
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
        BottomBarScreen(navController = navController)
    }
}