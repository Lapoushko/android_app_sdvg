package com.example.android_app_sdvg.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.android_app_sdvg.SDVGApplication
import com.example.android_app_sdvg.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Lapoushko
 *
 * Основная активность
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    SDVGApplication().SDVGNavHost()
                }
            }
        }
    }
}