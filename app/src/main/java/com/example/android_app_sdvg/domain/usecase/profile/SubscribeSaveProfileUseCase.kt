package com.example.android_app_sdvg.domain.usecase.profile

import com.example.android_app_sdvg.domain.entity.profile.Profile
import com.example.android_app_sdvg.domain.repo.ProfileRepository
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface SubscribeSaveProfileUseCase {
    suspend fun saveProfile(profile: Profile)
}

class SubscribeSaveProfileUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository
) : SubscribeSaveProfileUseCase{
    override suspend fun saveProfile(profile: Profile) {
        repository.saveProfile(profile)
    }

}