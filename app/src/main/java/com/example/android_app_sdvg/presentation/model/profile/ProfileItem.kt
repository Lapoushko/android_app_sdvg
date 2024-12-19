package com.example.android_app_sdvg.presentation.model.profile

import android.net.Uri
import android.os.Parcelable
import com.example.android_app_sdvg.presentation.util.URISerializer
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
    @Serializable(with = URISerializer::class)
    val photo: Uri,
    val dateBirthday: String
) : Parcelable