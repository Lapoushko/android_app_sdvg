package com.example.android_app_sdvg.domain.repo

import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 */
interface ClickRepository {
    /**
     * сохранить сохранения
     */
    suspend fun savePreferences(clicks: Int)

    /**
     * получить сохранения
     */
    fun getPreferences() : Flow<Int>

    /**
     * очистить сохранения
     */
    suspend fun clearPreferences()
}