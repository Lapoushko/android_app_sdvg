package com.example.android_app_sdvg.presentation.screen

import java.io.Serial

/**
 * @author Lapoushko
 *
 * Sealed класс для определения всех скринов
 */
sealed class Screen(
    val route: String
) {
    data object Main: Screen(route = MAIN_KEY)

    companion object{
        private const val MAIN_KEY = "MAIN"
    }
}