package com.example.android_app_sdvg.data.storage.entity

import com.example.android_app_sdvg.domain.entity.user.Sex
import java.util.Date

/**
 * Базовая модель пользователя для Room
 *
 * @author Lapoushko
 *
 * @param id айди пользователя
 * @param name имя пользователя
 * @param mail почта пользователя
 * @param birthday дата рождения
 * @param sex пол пользователя
 */
data class UserDb(
    val id: Long,
    val name: String,
    val mail: String,
    val birthday: Date,
    val sex: Sex
)