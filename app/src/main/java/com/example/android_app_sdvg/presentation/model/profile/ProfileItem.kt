package com.example.android_app_sdvg.presentation.model.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 */
@Serializable
@Parcelize
data class ProfileItem(
    val name: String,
    val email: String,
    val sex: String,
    val photo: String,
    val dateBirthday: String
) : Parcelable