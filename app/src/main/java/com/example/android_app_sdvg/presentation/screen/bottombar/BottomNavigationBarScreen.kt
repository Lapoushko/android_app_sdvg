package com.example.android_app_sdvg.presentation.screen.bottombar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_app_sdvg.presentation.navigation.graph.BottomNavigationBarGraph
import com.example.android_app_sdvg.presentation.navigation.ui.BottomNavigationBar

/**
 * @author Lapoushko
 *
 * Нижний бар навигации
 * @param navController контроллер навигации
 */
@Composable
fun BottomNavigationBarScreen(navController: NavHostController) {
    Scaffold(
        Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            BottomNavigationBarGraph(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarScreenPreview() {
    BottomNavigationBarScreen(navController = rememberNavController())
}