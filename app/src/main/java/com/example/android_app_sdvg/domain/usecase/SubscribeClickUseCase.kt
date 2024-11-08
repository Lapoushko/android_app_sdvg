package com.example.android_app_sdvg.domain.usecase

import javax.inject.Inject

/**
 * @author Lapoushko
 * клик в кликере
 */
interface SubscribeClickUseCase {
    /**
     * увеличить количество кликов на 1
     */
    suspend fun click()
}

class SubscribeClickUseCaseImpl @Inject constructor(

) : SubscribeClickUseCase{
    /**
     * увеличить количество кликов на 1
     */
    override suspend fun click() {
        TODO("Not yet implemented")
    }

}