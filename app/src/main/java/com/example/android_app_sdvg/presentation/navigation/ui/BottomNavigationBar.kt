package com.example.android_app_sdvg.presentation.navigation.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.presentation.theme.Typography

import com.example.android_app_sdvg.presentation.screen.Screen
import com.example.android_app_sdvg.presentation.screen.extension.AddItem

/**
 * @author Lapoushko
 *
 * Отображение нижней панели навигации
 */

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
) {
    val items = listOf(
        Screen.Clicker,
        Screen.Calendar,
        Screen.PersonalAccount
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { screen ->
            AddItem(
                screen = screen,
                destination = destination,
                navController = navController
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        rememberNavController()
    )
}