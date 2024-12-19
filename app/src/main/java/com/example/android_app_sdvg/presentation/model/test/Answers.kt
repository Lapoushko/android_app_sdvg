package com.example.android_app_sdvg.presentation.model.test

/**
 * @author Lapoushko
 */
enum class Answers(
    val naming: String,
    val value: Int
) {
    ABSOLUTELY_DISAGREE("Абсолютно не согласен",1),
    RATHER_DISAGREE("Скорее согласен",2),
    NOT_SURE("Не уверен",3),
    RATHER_AGREE("Скорее согласен",4),
    ABSOLUTELY_AGREE("Абсолютно согласен",5)
}