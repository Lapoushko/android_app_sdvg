package com.example.android_app_sdvg.presentation.handler

import androidx.navigation.NavHostController

/**
 * @author Lapoushko
 * Нажатия кнопок календаря
 */
class TaskerScreenHandler(val navController: NavHostController) {

    fun openCalendar(showModal: Boolean): Boolean{
        return !showModal
    }

}
