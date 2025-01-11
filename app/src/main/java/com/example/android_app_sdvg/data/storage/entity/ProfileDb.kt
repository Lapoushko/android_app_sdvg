package com.example.android_app_sdvg.data.storage.entity

import com.example.android_app_sdvg.domain.entity.profile.Sex
import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 */
@Serializable
data class ProfileDb(
    val name: String? = "",
    val email: String? = "",
    val sex: Sex? = null,
    val photo: String? = "",
    val dateBirthday: Long? = null
)