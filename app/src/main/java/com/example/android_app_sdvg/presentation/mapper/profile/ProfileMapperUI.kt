package com.example.android_app_sdvg.presentation.mapper.profile

import com.example.android_app_sdvg.domain.entity.profile.Profile
import com.example.android_app_sdvg.domain.entity.profile.getSex
import com.example.android_app_sdvg.presentation.extension.toDateString
import com.example.android_app_sdvg.presentation.extension.toFormattedString
import com.example.android_app_sdvg.presentation.extension.toFormattedUri
import com.example.android_app_sdvg.presentation.extension.toLongDate
import com.example.android_app_sdvg.presentation.model.profile.ProfileItem
import javax.inject.Inject

/**
 * @author Lapoushko
 * маппер профиля
 */
interface ProfileMapperUI {
    fun toDomain(profileItem: ProfileItem) : Profile

    fun toUi(profile: Profile) : ProfileItem
}

class ProfileMapperUIImpl @Inject constructor() : ProfileMapperUI{
    override fun toDomain(profileItem: ProfileItem): Profile {
        return Profile(
            name = profileItem.name.ifEmpty { EMPTY },
            email = profileItem.email.ifEmpty { EMPTY },
            sex = profileItem.sex.getSex(),
            photo = profileItem.photo.toString(),
            dateBirthday = profileItem.dateBirthday.toLongDate(isRu = false)
        )
    }

    override fun toUi(profile: Profile): ProfileItem {
        return ProfileItem(
            name = if (profile.name == EMPTY) "" else profile.name,
            email = if (profile.email == EMPTY) "" else profile.email,
            sex = profile.sex.naming,
            photo = profile.photo.toFormattedUri(),
            dateBirthday = profile.dateBirthday.toDateString(isRu = false)
        )
    }
    companion object{
        private const val EMPTY = "Не указано"
    }
}