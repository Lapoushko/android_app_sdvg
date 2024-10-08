package com.example.android_app_sdvg.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.android_app_sdvg.SDVGApplication
import com.example.android_app_sdvg.presentation.theme.Android_app_sdvgTheme
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
            Android_app_sdvgTheme {
                SDVGApplication().SDVGNavHost()
            }
        }
    }
}