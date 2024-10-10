package com.example.android_app_sdvg.presentation.handler

import android.util.Log
import androidx.navigation.NavHostController
import com.example.android_app_sdvg.util.Constants

/**
 * @author Lapoushko
 * Нажатия кнопок календаря
 */
class CalendarScreenHandler(navController: NavHostController) : AbstractScreenHandler() {
    fun onToScreen() {
        Log.d(Constants.LOG_KEY, "Click to calendar")
    }
}
