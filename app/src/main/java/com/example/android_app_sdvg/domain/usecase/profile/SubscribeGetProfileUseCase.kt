package com.example.android_app_sdvg.domain.usecase.profile

import com.example.android_app_sdvg.domain.entity.profile.Profile
import com.example.android_app_sdvg.domain.repo.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface SubscribeGetProfileUseCase {
    fun getProfile() : Flow<Profile>
}

class SubscribeGetProfileUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository
) : SubscribeGetProfileUseCase{
    override fun getProfile(): Flow<Profile> {
        return repository.getProfile()
    }
}