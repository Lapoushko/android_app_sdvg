package com.example.android_app_sdvg.presentation.test

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.usecase.test.SubscribeGetTest
import com.example.android_app_sdvg.domain.usecase.test.SubscribeResultTest
import com.example.android_app_sdvg.domain.usecase.test.SubscribeStatusTest
import com.example.android_app_sdvg.presentation.model.test.Answers
import com.example.android_app_sdvg.presentation.model.test.TestItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class TestingScreenViewModel @Inject constructor(
    private val statusTestUseCase : SubscribeStatusTest,
    private val getTestUseCase: SubscribeGetTest,
    private val resultTestUseCase: SubscribeResultTest,
) : ViewModel() {
    private var _state = MutableTestingScreenState()
    val state = _state as TestingScreenState

    init {
        loadTest()
    }

    private fun loadTest(){
        viewModelScope.launch {
            _state.tests = getTestUseCase.getTest()
        }
    }

    fun updateAnswer(answer: Answers, test: TestItem) {
        _state.tests.find { it == test }?.answer = answer
    }

    fun save(onSave: () -> Unit) {
        viewModelScope.launch {
            if (!state.tests.any { it.answer == null }) {
                onSave()
                val result = state.tests.sumOf { it.answer?.value ?: 0 }
                resultTestUseCase.saveTestResult(result)
//                statusTestUseCase.passTest()
            }
        }
    }

    private class MutableTestingScreenState : TestingScreenState {
        override var tests: List<TestItem> by mutableStateOf(emptyList())
    }
}