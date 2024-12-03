package com.example.android_app_sdvg.presentation.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.TouchApp
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author Lapoushko
 *
 * Sealed класс для определения всех скринов
 * @param route маршрут
 * @param title имя экрана
 * @param setIcon иконка для активного элемента
 * @param unsetIcon иконка для неактивного элемента
 */
@Immutable
sealed class ScreenBar(
    val route: String,
    val title: String,
    val setIcon: ImageVector?,
    val unsetIcon: ImageVector?,
) {
    /**
     * Экран календаря
     */
    data object Tasker : ScreenBar(
        route = TASKER_KEY,
        title = TASKER_NAME,
        setIcon = Icons.Filled.DateRange,
        unsetIcon = Icons.Outlined.DateRange
    )

    /**
     * экран кликера
     */
    data object Clicker : ScreenBar(
        route = CLICKER_KEY,
        title = CLICKER_NAME,
        setIcon = Icons.Filled.TouchApp,
        unsetIcon = Icons.Outlined.TouchApp
    )

    /**
     * Экран личного кабинета
     */
    data object PersonalAccount : ScreenBar(
        route = PERSONAL_ACCOUNT_KEY,
        title = PERSONAL_ACCOUNT_NAME,
        setIcon = Icons.Filled.AccountCircle,
        unsetIcon = Icons.Outlined.AccountCircle
    )

    companion object {
        private const val TASKER_NAME = "Задачник"
        private const val TASKER_KEY = "TASKER"

        private const val CLICKER_NAME = "Кликер"
        private const val CLICKER_KEY = "CLICKER"

        private const val PERSONAL_ACCOUNT_NAME = "Личный кабинет"
        private const val PERSONAL_ACCOUNT_KEY = "PERSONAL ACCOUNT"
    }
}