package com.example.android_app_sdvg.presentation.profile.editor

/**
 * @author Lapoushko
 */
interface EditProfileScreenState{
    val name: Input?
    val email: Input?
    val sex: Input?
    val photo: Input?
    val dateBirthday: Input?
    val isNeedToShowDatePicker: Boolean
    val isNeedToShowPermission: Boolean
    val isNeedToShowSelect: Boolean
}
class Input(
    var text: String,
    var error: String?
)