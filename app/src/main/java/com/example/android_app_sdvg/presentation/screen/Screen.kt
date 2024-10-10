package com.example.android_app_sdvg.presentation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author Lapoushko
 *
 * Sealed класс для определения всех скринов
 */
@Immutable
sealed class Screen(
    val route: String,
    val title: String,
    val setIcon: ImageVector,
    val unsetIcon: ImageVector,
) {
    /**
     * Экран календаря
     */
    data object Calendar : Screen(
        route = CALENDAR_KEY,
        title = CALENDAR_NAME,
        setIcon = Icons.Filled.DateRange,
        unsetIcon = Icons.Outlined.DateRange
    )

    /**
     * экран кликера
     */
    data object Clicker : Screen(
        route = CLICKER_KEY,
        title = CLICKER_NAME,
        setIcon = Icons.Filled.PlayArrow,
        unsetIcon = Icons.Outlined.PlayArrow
    )

    /**
     * Экран личного кабинета
     */
    data object PersonalAccount : Screen(
        route = PERSONAL_ACCOUNT_KEY,
        title = PERSONAL_ACCOUNT_NAME,
        setIcon = Icons.Filled.Person,
        unsetIcon = Icons.Outlined.Person
    )

    companion object {
        private const val CALENDAR_KEY = "CALENDAR"
        private const val CLICKER_KEY = "CLICKER"
        private const val PERSONAL_ACCOUNT_KEY = "PERSONAL ACCOUNT"

        private const val CALENDAR_NAME = "Календарь"
        private const val CLICKER_NAME = "Кликер"
        private const val PERSONAL_ACCOUNT_NAME = "Личный кабинет"
    }
}