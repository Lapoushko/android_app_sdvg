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
    private val errors: MutableSet<Errors> = Errors.entries.toMutableSet()

    init {
        _profileState.isNeedToShowPermission = false
    }

    fun updateProfile(profile: ProfileItem) {
        _profileItem.value = profile
    }

    fun updateName(input: String) {
        _profileState.name = checkErrorInput(
            input = input,
            error = Errors.NAME_ERROR,
            input.isNotEmpty()
        )
    }

    fun updateEmail(input: String) {
        _profileState.email = checkErrorInput(
            input = input,
            error = Errors.EMAIL_ERROR,
            (input.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(input).matches())
        )
    }


    fun updateSex(input: String) {
        _profileState.sex = checkErrorInput(
            input = input,
            error = Errors.SEX_ERROR,
            (input.isNotEmpty())
        )
    }

    fun updateDateBirthday(input: String) {
        val error = Errors.DATE_ERROR
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
        error: Errors,
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
        override var name: Input by mutableStateOf(Input(text = "", error = Errors.NAME_ERROR))
        override var email: Input by mutableStateOf(Input(text = "", error = Errors.EMAIL_ERROR))
        override var sex: Input by mutableStateOf(Input(text = "", error = Errors.SEX_ERROR))
        override var photo: Input by mutableStateOf(Input(text = "", error = Errors.PHOTO_ERROR))
        override var dateBirthday: Input by mutableStateOf(Input("", Errors.DATE_ERROR))
        override var isNeedToShowDatePicker: Boolean by mutableStateOf(false)
        override var isNeedToShowPermission: Boolean by mutableStateOf(false)
        override var isNeedToShowSelect: Boolean by mutableStateOf(false)
    }
}