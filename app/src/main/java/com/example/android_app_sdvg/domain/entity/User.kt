package com.example.android_app_sdvg.domain.entity

import androidx.compose.runtime.Immutable
import java.util.Date

/**
 * Базовая модель пользователя
 * @author Lapoushko
 *
 * @param name имя пользователя
 * @param mail почта пользователя
 * @param birthday дата рождения
 * @param sex пол пользователя
 */
@Immutable
data class User(
    val name: String,
    val mail: String,
    val birthday: Date,
    val sex: Sex
)