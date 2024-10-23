package com.example.android_app_sdvg.presentation.create_task

import androidx.navigation.NavHostController
import com.example.android_app_sdvg.presentation.model.screen.ScreenItem
import com.example.android_app_sdvg.presentation.navigation.screen.ScreenBar

/**
 * @author Lapoushko
 */
class CreateTaskScreenHandler(val navController: NavHostController) {
    fun onToBack(){
        navController.navigate(ScreenBar.Tasker.route)
    }
}