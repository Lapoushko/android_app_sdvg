package com.example.android_app_sdvg.presentation.profile.editor

import com.example.android_app_sdvg.presentation.model.input.Input
import com.example.android_app_sdvg.presentation.model.input.ProfileErrors

/**
 * @author Lapoushko
 */
interface EditProfileScreenState{
    val name: Input<ProfileErrors>?
    val email: Input<ProfileErrors>?
    val sex: Input<ProfileErrors>?
    val photo: Input<ProfileErrors>?
    val dateBirthday: Input<ProfileErrors>?
    val isNeedToShowDatePicker: Boolean
    val isNeedToShowPermission: Boolean
    val isNeedToShowSelect: Boolean
}