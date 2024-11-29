package com.example.android_app_sdvg.presentation.adding.abstracting

import androidx.navigation.NavHostController
import com.example.android_app_sdvg.presentation.navigation.screen.ScreenBar

/**
 * @author Lapoushko
 */
abstract class AbstractAddTaskScreenHandler(val navController: NavHostController) {
    /**
     * вернуться на экран задачника
     */
    fun onToBack(){
        navController.navigate(ScreenBar.Tasker.route)
    }
}