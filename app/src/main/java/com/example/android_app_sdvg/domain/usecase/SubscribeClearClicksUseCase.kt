package com.example.android_app_sdvg.domain.usecase

import javax.inject.Inject

/**
 * @author Lapoushko
 * удалить все клики
 */
interface SubscribeClearClicksUseCase {
    /**
     * удаление кликов
     */
    suspend fun clear()
}

class SubscribeClearClicksUseCaseImpl @Inject constructor() : SubscribeClearClicksUseCase{
    /**
     * удаление кликов
     */
    override suspend fun clear() {
        TODO("Not yet implemented")
    }

}