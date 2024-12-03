package com.example.android_app_sdvg.presentation.profile.editor

import android.net.Uri
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_sdvg.domain.usecase.profile.SubscribeSaveProfileUseCase
import com.example.android_app_sdvg.presentation.extension.toLongDate
import com.example.android_app_sdvg.presentation.mapper.profile.ProfileMapperUI
import com.example.android_app_sdvg.presentation.model.profile.ProfileItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class EditProfileScreenViewModel @Inject constructor(
    private val saveProfileUseCase: SubscribeSaveProfileUseCase,
    private val mapperUI: ProfileMapperUI
) : ViewModel() {
    private var _profileState = MutableEditProfileScreenState()
    val profileState = _profileState as EditProfileScreenState

    private var _profileItem: MutableStateFlow<ProfileItem?> = MutableStateFlow(null)
    private val errors: MutableSet<String> = mutableSetOf()

    init {
        _profileState.isNeedToShowPermission = false
    }

    fun updateProfile(profile: ProfileItem) {
        _profileItem.value = profile
    }

    fun updateName(input: String) {
        val error = "Неправильное имя"
        _profileState.name = checkErrorInput(
            input = input,
            error = error,
            input.isNotEmpty()
        )
    }

    fun updateEmail(input: String) {
        val error = "Неправильная почта"
        _profileState.email = checkErrorInput(
            input = input,
            error = error,
            (input.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(input).matches())
        )
    }


    fun updateSex(input: String) {
        val error = "Неправильный пол"
        _profileState.sex = checkErrorInput(
            input = input,
            error = error,
            (input.isNotEmpty())
        )
    }

    fun updateDateBirthday(input: String) {
        val error = "Неправильная дата"
        try {
            input.toLongDate()
            _profileState.dateBirthday = Input(
                text = input,
                error = null
            )
            errors.remove(error)
        } catch (_: Exception) {
            _profileState.dateBirthday = Input(
                text = input,
                error = error
            )
            errors.add(error)
        }
    }

    fun save(onToBack: () -> Unit) {
        validate()
        if (errors.size == 0) {
            viewModelScope.launch {
                val profile = ProfileItem(
                    name = profileState.name?.text ?: "",
                    email = profileState.email?.text ?: "",
                    sex = profileState.sex?.text ?: "",
                    dateBirthday = profileState.dateBirthday?.text ?: "0",
                    photo = profileState.photo?.text ?: ""
                )
                saveProfileUseCase.saveProfile(mapperUI.toDomain(profile))
                onToBack()
            }
        }
    }

    fun toggleDatePicker() {
        _profileState.isNeedToShowDatePicker = !_profileState.isNeedToShowDatePicker
    }

    fun onToggleGallery() {
        _profileState.isNeedToShowSelect =  !_profileState.isNeedToShowSelect
    }

    fun onPermissionClosed() {
        _profileState.isNeedToShowPermission = false
    }

    fun onImageSelected(uri: Uri?) {
        uri?.let {
            _profileState.photo = Input(
                text = it.toString(),
                error = null
            )
        }
    }

    private fun validate() {
        updateDateBirthday(profileState.dateBirthday?.text ?: "")
        updateEmail(profileState.email?.text ?: "")
        updateSex(profileState.sex?.text ?: "")
        updateName(profileState.name?.text ?: "")
    }

    private fun checkErrorInput(
        input: String,
        error: String,
        isCorrect: Boolean,
    ): Input {
        if (isCorrect) {
            errors.remove(error)
            return Input(text = input, error = null)
        } else {
            errors.add(error)
            return Input(text = input, error = error)
        }
    }

    private class MutableEditProfileScreenState : EditProfileScreenState {
        val inputInit = Input(text = "", error = null)
        override var name: Input by mutableStateOf(inputInit)
        override var email: Input by mutableStateOf(inputInit)
        override var sex: Input by mutableStateOf(inputInit)
        override var photo: Input by mutableStateOf(inputInit)
        override var dateBirthday: Input by mutableStateOf(Input("", null))
        override var isNeedToShowDatePicker: Boolean by mutableStateOf(false)
        override var isNeedToShowPermission: Boolean by mutableStateOf(false)
        override var isNeedToShowSelect: Boolean by mutableStateOf(false)
    }
}