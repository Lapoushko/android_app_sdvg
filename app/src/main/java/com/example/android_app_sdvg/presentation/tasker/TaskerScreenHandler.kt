package com.example.android_app_sdvg.presentation.tasker

import androidx.navigation.NavHostController
import com.example.android_app_sdvg.presentation.model.screen.ScreenItem
import com.example.android_app_sdvg.presentation.navigation.screen.ScreenBar

/**
 * @author Lapoushko
 * Нажатия кнопок календаря
 */
class TaskerScreenHandler(val navController: NavHostController) {
    fun onToCreateTask(dateStart: Long){
        navController.navigate(ScreenItem.CreateTask(dateStart = dateStart))
    }
}
