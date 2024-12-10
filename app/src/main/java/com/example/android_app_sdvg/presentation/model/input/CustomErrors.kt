package com.example.android_app_sdvg.presentation.model.input

/**
 * @author Lapoushko
 */
interface CustomErrors {
    val naming: String
}

fun <E : CustomErrors> String.checkErrorInput(
    error: E?,
    adding: () -> Unit = {},
    removing: () -> Unit = {},
    isCorrect: Boolean
) : Input<E> {
    if (isCorrect) {
        removing()
        return Input(text = this, error = null)
    } else {
        adding()
        return Input(text = this, error = error)
    }
}

enum class ProfileErrors(override val naming: String) : CustomErrors{
    NAME_ERROR("Неправильное имя"),
    EMAIL_ERROR("Неправильная почта"),
    SEX_ERROR("Не указан пол"),
    DATE_ERROR("Неправильная дата"),
}
enum class TaskErrors(override val naming: String) : CustomErrors{
    NAME_ERROR("Неправильное название"),
//    DESC_ERROR("Неправильное описание"),
    PRIORITY_ERROR("Приоритет не выбран"),
    CATEGORY_ERROR("Категория не выбрана"),
    PERIODICITY_ERROR("Периодичность не выбрана"),
}