package com.example.android_app_sdvg.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.usecase.profile.SubscribeGetProfileUseCase
import com.example.android_app_sdvg.presentation.mapper.profile.ProfileMapperUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getProfileUseCase: SubscribeGetProfileUseCase,
    private val mapperUI: ProfileMapperUI
): ViewModel() {
    private var _profileState = MutableProfileScreenState()
    val profileState = _profileState as ProfileScreenState

    init {
        load()
    }

    private fun load(){
        viewModelScope.launch {
            getProfileUseCase.getProfile().collect{ profile ->
                mapperUI.toUi(profile).apply {
                    _profileState.name = name
                    _profileState.email = email
                    _profileState.photo = photo
                    _profileState.sex = sex
                    _profileState.dateBirthday = dateBirthday
                }
            }
        }
    }

    private class MutableProfileScreenState(
    ) : ProfileScreenState{
        override var name: String by mutableStateOf("")
        override var email: String by mutableStateOf("")
        override var photo: String by mutableStateOf("")
        override var sex: String by mutableStateOf("")
        override var dateBirthday: String by mutableStateOf("")
        override var resultLastTest: String by mutableStateOf("")
        override var recommendations: List<String> by mutableStateOf(emptyList())
        override var isClosedTest: Boolean by mutableStateOf(false)
        override var remainingOpeningTime: String by mutableStateOf("")
    }
}