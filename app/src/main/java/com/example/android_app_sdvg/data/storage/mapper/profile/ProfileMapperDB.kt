package com.example.android_app_sdvg.data.storage.mapper.profile

import com.example.android_app_sdvg.data.storage.entity.ProfileDb
import com.example.android_app_sdvg.domain.entity.profile.Profile
import com.example.android_app_sdvg.domain.entity.profile.Sex
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface ProfileMapperDB {
    fun toDb(profile: Profile): ProfileDb

    fun toDomain(profileDb: ProfileDb): Profile
}

class ProfileMapperDBImpl @Inject constructor() : ProfileMapperDB{
    override fun toDb(profile: Profile): ProfileDb {
        return ProfileDb(
            name = profile.name,
            email = profile.email,
            sex = profile.sex,
            photo = profile.photo,
            dateBirthday = profile.dateBirthday
        )
    }

    override fun toDomain(profileDb: ProfileDb): Profile {
        return Profile(
            name = profileDb.name ?: "",
            email = profileDb.email ?: "",
            sex = profileDb.sex ?: Sex.OTHER,
            photo = profileDb.photo ?: "",
            dateBirthday = profileDb.dateBirthday ?: 0L
        )
    }
}