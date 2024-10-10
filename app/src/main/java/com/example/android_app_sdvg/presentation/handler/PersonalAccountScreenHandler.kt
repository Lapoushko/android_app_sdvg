package com.example.android_app_sdvg.presentation.handler

import android.util.Log
import androidx.navigation.NavHostController
import com.example.android_app_sdvg.util.Constants

/**
 * @author Lapoushko
 * Реализация личного кабинета задач
 */
class PersonalAccountScreenHandler(navController: NavHostController) : AbstractScreenHandler() {
    fun onToScreen() {
        Log.d(Constants.LOG_KEY, "Click to personal account")
    }

}