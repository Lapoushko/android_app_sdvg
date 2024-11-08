package com.example.android_app_sdvg.domain.usecase

import javax.inject.Inject

/**
 * @author Lapoushko
 * получить количество кликов
 */
interface SubscribeGetClicksUseCase {
    /**
     * получить количество кликов
     */
    suspend fun getClicks() : Int
}

class SubscribeGetClicksUseCaseImpl @Inject constructor() : SubscribeGetClicksUseCase{
    /**
     * получить количество кликов
     */
    override suspend fun getClicks(): Int {
        TODO("Not yet implemented")
    }

}