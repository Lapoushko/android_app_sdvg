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
    var error: Errors?
)
enum class Errors(val naming: String){
    NAME_ERROR("Неправильное имя"),
    EMAIL_ERROR("Неправильная почта"),
    SEX_ERROR("Не указан пол"),
    DATE_ERROR("Неправильная дата"),
    PHOTO_ERROR("Неправильное фото")
}