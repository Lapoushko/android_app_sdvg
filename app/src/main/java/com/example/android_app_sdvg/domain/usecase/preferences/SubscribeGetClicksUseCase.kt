package com.example.android_app_sdvg.domain.usecase.preferences

import com.example.android_app_sdvg.domain.repo.ClickRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 * получить количество кликов
 */
interface SubscribeGetClicksUseCase {
    /**
     * получить количество кликов
     */
    fun getClicks() : Flow<Int>
}

class SubscribeGetClicksUseCaseImpl @Inject constructor(
    private val repository: ClickRepository
) : SubscribeGetClicksUseCase {
    /**
     * получить количество кликов
     */
    override fun getClicks(): Flow<Int> {
        return repository.getPreferences()
    }

}