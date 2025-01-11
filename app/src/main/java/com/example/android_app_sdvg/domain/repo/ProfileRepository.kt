package com.example.android_app_sdvg.domain.repo

import com.example.android_app_sdvg.domain.entity.profile.Profile
import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 */
interface ProfileRepository {
    suspend fun saveProfile(profile: Profile)

    fun getProfile(): Flow<Profile>
}