package com.example.android_app_sdvg.presentation.clicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.usecase.preferences.SubscribeClickUseCase
import com.example.android_app_sdvg.domain.usecase.preferences.SubscribeGetClicksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 * ViewModel для кликера
 */
@HiltViewModel
class ClickerScreenViewModel @Inject constructor(
    private val subscribeGetClicksUseCase: SubscribeGetClicksUseCase,
    private val subscribeClickUseCase: SubscribeClickUseCase
): ViewModel(){

    private val _countClick: MutableStateFlow<Int> = MutableStateFlow(0)
    val countClick: StateFlow<Int> = _countClick.asStateFlow()

    init {
        getClicks()
    }

    private fun getClicks(){
        viewModelScope.launch {
            _countClick.value = subscribeGetClicksUseCase.getClicks().first()
        }
    }

    fun click(){
        viewModelScope.launch {
            _countClick.value++
            subscribeClickUseCase.click(countClick.value)
        }
    }

    fun clearClicks(){
        viewModelScope.launch {
            _countClick.value = 0
            subscribeClickUseCase.click(countClick.value)
        }
    }
}