package com.example.android_app_sdvg.domain.usecase.preferences

import com.example.android_app_sdvg.domain.repo.ClickRepository
import javax.inject.Inject

/**
 * @author Lapoushko
 * клик в кликере
 */
interface SubscribeClickUseCase {
    /**
     * увеличить количество кликов на 1
     */
    suspend fun click(countClicks: Int)
}

class SubscribeClickUseCaseImpl @Inject constructor(
    private val repository: ClickRepository
) : SubscribeClickUseCase {
    /**
     * увеличить количество кликов на 1
     */
    override suspend fun click(countClicks: Int) {
        repository.savePreferences(countClicks)
    }

}