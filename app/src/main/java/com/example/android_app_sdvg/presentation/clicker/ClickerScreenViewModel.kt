package com.example.android_app_sdvg.presentation.clicker

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * @author Lapoushko
 * ViewModel для кликера
 */
@HiltViewModel
class ClickerScreenViewModel @Inject constructor(

): ViewModel(){

    private val _countClick: MutableStateFlow<Int> = MutableStateFlow(0)
    val countClick: StateFlow<Int> = _countClick.asStateFlow()

    fun click(){

    }
}