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
import com.example.android_app_sdvg.presentation.model.input.Input
import com.example.android_app_sdvg.presentation.model.input.ProfileErrors
import com.example.android_app_sdvg.presentation.model.input.checkErrorInput
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
    private val errors: MutableSet<ProfileErrors> = ProfileErrors.entries.toMutableSet()

    init {
        _profileState.isNeedToShowPermission = false
    }

    fun updateProfile(profile: ProfileItem) {
        _profileItem.value = profile
    }

    fun updateName(input: String) {
        val error = ProfileErrors.NAME_ERROR
        _profileState.name = input.checkErrorInput(
            error = error,
            adding = { errors.add(error) },
            removing = { errors.remove(error) },
            isCorrect = input.isNotEmpty()
        )
    }

    fun updateEmail(input: String) {
        val error = ProfileErrors.EMAIL_ERROR
        _profileState.email = input.checkErrorInput(
            error = error,
            adding = { errors.add(error) },
            removing = { errors.remove(error) },
            isCorrect = (input.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(input).matches())
        )
    }


    fun updateSex(input: String) {
        val error = ProfileErrors.SEX_ERROR
        _profileState.sex = input.checkErrorInput(
            error = error,
            adding = { errors.add(error) },
            removing = { errors.remove(error) },
            (input.isNotEmpty())
        )
    }

    fun updateDateBirthday(input: String) {
        val error = ProfileErrors.DATE_ERROR
        try {
            if(input.toLongDate(isRu = false) <= System.currentTimeMillis()) {
                _profileState.dateBirthday = Input(
                    text = input,
                    error = null
                )
                errors.remove(error)
            }
            else {
                _profileState.dateBirthday = Input(
                    text = input,
                    error = error
                )
            }
        } catch (_: Exception) {
            _profileState.dateBirthday = Input(
                text = input,
                error = error
            )
            errors.add(error)
        }
    }

    fun save(onToBack: () -> Unit) {
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
        _profileState.isNeedToShowSelect = !_profileState.isNeedToShowSelect
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

    private class MutableEditProfileScreenState : EditProfileScreenState {
        override var name: Input<ProfileErrors>? by mutableStateOf(
            Input(
                text = "",
                error = ProfileErrors.NAME_ERROR
            )
        )
        override var email: Input<ProfileErrors>? by mutableStateOf(
            Input(
                text = "",
                error = ProfileErrors.EMAIL_ERROR
            )
        )
        override var sex: Input<ProfileErrors>? by mutableStateOf(
            Input(
                text = "",
                error = ProfileErrors.SEX_ERROR
            )
        )
        override var photo: Input<ProfileErrors>? by mutableStateOf(
            Input(
                text = "",
                error = null
            )
        )
        override var dateBirthday: Input<ProfileErrors>? by mutableStateOf(
            Input(
                "",
                ProfileErrors.DATE_ERROR
            )
        )
        override var isNeedToShowDatePicker: Boolean by mutableStateOf(false)
        override var isNeedToShowPermission: Boolean by mutableStateOf(false)
        override var isNeedToShowSelect: Boolean by mutableStateOf(false)
    }
}