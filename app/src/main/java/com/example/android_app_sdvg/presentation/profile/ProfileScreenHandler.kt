package com.example.android_app_sdvg.presentation.profile

import androidx.navigation.NavHostController
import com.example.android_app_sdvg.presentation.model.profile.ProfileItem
import com.example.android_app_sdvg.presentation.model.screen.ScreenItem

/**
 * @author Lapoushko
 * Реализация личного кабинета задач
 * @param navController контроллер навигации
 */
class ProfileScreenHandler(private val navController: NavHostController) {
    fun onToEdit(profileItem: ProfileItem){
        navController.navigate(ScreenItem.EditProfile(profile = profileItem))
    }
}