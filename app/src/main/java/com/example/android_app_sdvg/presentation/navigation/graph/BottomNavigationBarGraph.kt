package com.example.android_app_sdvg.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.android_app_sdvg.presentation.adding.creator.CreateTaskScreen
import com.example.android_app_sdvg.presentation.adding.creator.CreateTaskScreenHandler
import com.example.android_app_sdvg.presentation.adding.editor.EditTaskScreen
import com.example.android_app_sdvg.presentation.adding.editor.EditTaskScreenHandler
import com.example.android_app_sdvg.presentation.clicker.ClickerScreen
import com.example.android_app_sdvg.presentation.model.profile.ProfileItem
import com.example.android_app_sdvg.presentation.model.screen.ScreenItem
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import com.example.android_app_sdvg.presentation.navigation.screen.ScreenBar
import com.example.android_app_sdvg.presentation.profile.ProfileScreen
import com.example.android_app_sdvg.presentation.profile.ProfileScreenHandler
import com.example.android_app_sdvg.presentation.profile.editor.EditProfileScreen
import com.example.android_app_sdvg.presentation.tasker.TaskerScreen
import com.example.android_app_sdvg.presentation.tasker.TaskerScreenHandler
import com.example.android_app_sdvg.presentation.test.TestingScreen
import com.example.android_app_sdvg.presentation.util.CustomNavTypeProfile
import com.example.android_app_sdvg.presentation.util.CustomNavTypeTask
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
            ClickerScreen()
        }
        composable(route = ScreenBar.PersonalAccount.route) {
            ProfileScreen(
                profileScreenHandler = ProfileScreenHandler(
                    navController = navController
                )
            )
        }
        composable<ScreenItem.CreateTask> {
            CreateTaskScreen(
                handler = CreateTaskScreenHandler(navController = navController)
            )
        }

        composable<ScreenItem.EditTask>(
            typeMap = mapOf(typeOf<TaskItem>() to CustomNavTypeTask)
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
                    categoryItem = task.task.categoryItem,
                    taskStatus = task.task.taskStatus
                )
            )
        }

        composable<ScreenItem.EditProfile>(
            typeMap = mapOf(typeOf<ProfileItem>() to CustomNavTypeProfile)
        ) { backStackEntry ->
            val profile = backStackEntry.toRoute<ScreenItem.EditProfile>()
            EditProfileScreen(
                onToBack = { navController.popBackStack() },
                profile = ProfileItem(
                    name = profile.profile.name,
                    email = profile.profile.email,
                    sex = profile.profile.sex,
                    photo = profile.profile.photo,
                    dateBirthday = profile.profile.dateBirthday
                )
            )
        }

        composable<ScreenItem.Test> {
            TestingScreen(
                onToBack = { navController.popBackStack() }
            )
        }
    }
}