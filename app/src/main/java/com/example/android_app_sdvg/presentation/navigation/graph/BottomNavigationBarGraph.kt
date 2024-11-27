package com.example.android_app_sdvg.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.android_app_sdvg.presentation.clicker.ClickerScreenHandler
import com.example.android_app_sdvg.presentation.personal_account.PersonalAccountScreenHandler
import com.example.android_app_sdvg.presentation.tasker.TaskerScreenHandler
import com.example.android_app_sdvg.presentation.clicker.ClickerScreen
import com.example.android_app_sdvg.presentation.adding.create_task.CreateTaskScreen
import com.example.android_app_sdvg.presentation.adding.create_task.CreateTaskScreenHandler
import com.example.android_app_sdvg.presentation.adding.edit_task.EditTaskScreen
import com.example.android_app_sdvg.presentation.adding.edit_task.EditTaskScreenHandler
import com.example.android_app_sdvg.presentation.model.screen.ScreenItem
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.presentation.personal_account.PersonalAccountScreen
import com.example.android_app_sdvg.presentation.navigation.screen.ScreenBar
import com.example.android_app_sdvg.presentation.tasker.TaskerScreen
import com.example.android_app_sdvg.presentation.util.CustomNavType
import com.example.android_app_sdvg.presentation.util.PrimitiveNavType
import kotlin.reflect.typeOf

/**
 * @author Lapoushko
 * Граф навигации нижнего бара
 * @param navController контроллер навигации
 */

@Composable
fun BottomNavigationBarGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenBar.Tasker.route
    ) {
        composable(route = ScreenBar.Tasker.route) {
            TaskerScreen(
                taskerScreenHandler = TaskerScreenHandler(
                    navController = navController
                )
            )
        }
        composable(route = ScreenBar.Clicker.route) {
            ClickerScreen(
                clickerScreenHandler = ClickerScreenHandler(
                    navController = navController
                )
            )
        }
        composable(route = ScreenBar.PersonalAccount.route) {
            PersonalAccountScreen(
                personalAccountScreenHandler = PersonalAccountScreenHandler(
                    navController = navController
                )
            )
        }
        composable<ScreenItem.CreateTask>(
            typeMap = mapOf(typeOf<Long>() to PrimitiveNavType(Long::class.java))
        ) { backStackEntry ->
            val createTask = backStackEntry.toRoute<ScreenItem.CreateTask>()
            CreateTaskScreen(
                dateStart = createTask.dateStart,
                handler = CreateTaskScreenHandler(navController = navController)
            )
        }

        composable<ScreenItem.EditTask>(
            typeMap = mapOf(typeOf<TaskItem>() to CustomNavType)
        ) { backStackEntry ->
            val task = backStackEntry.toRoute<ScreenItem.EditTask>()
            EditTaskScreen(
                handler = EditTaskScreenHandler(
                    navController = navController
                ),
                taskItem = TaskItem(
                    id = task.task.id,
                    name = task.task.name,
                    description = task.task.description,
                    dates = task.task.dates,
                    timer = task.task.timer,
                    capacity = task.task.capacity,
                    periodicity = task.task.periodicity,
                    priorityItem = task.task.priorityItem,
                    categoryItem = task.task.categoryItem
                )
            )
        }

    }
}