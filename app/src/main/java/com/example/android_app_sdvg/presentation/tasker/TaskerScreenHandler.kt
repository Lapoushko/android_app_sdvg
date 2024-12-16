package com.example.android_app_sdvg.presentation.tasker

import androidx.navigation.NavHostController
import com.example.android_app_sdvg.presentation.model.screen.ScreenItem
import com.example.android_app_sdvg.presentation.model.task.TaskItem

/**
 * @author Lapoushko
 * Нажатия кнопок календаря
 */
class TaskerScreenHandler(val navController: NavHostController) {
    fun onToCreateTask(){
        navController.navigate(ScreenItem.CreateTask)
    }

    fun onToFilter(){
        navController.navigate(ScreenItem.TaskerFilter)
    }

    fun onToEditTask(taskItem: TaskItem){
        navController.navigate(ScreenItem.EditTask(task = taskItem))
    }
}
