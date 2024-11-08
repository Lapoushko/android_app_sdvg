package com.example.android_app_sdvg.data.storage.repo

import com.example.android_app_sdvg.domain.repo.ClickRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 */
class ClickRepositoryImpl @Inject constructor() : ClickRepository{
    /**
     * сохранить сохранения
     */
    override suspend fun savePreferences(clicks: Int) {
        TODO("Not yet implemented")
    }

    /**
     * получить сохранения
     */
    override fun getPreferences(): Flow<Int> {
        TODO("Not yet implemented")
    }

    /**
     * очистить сохранения
     */
    override suspend fun clearPreferences() {
        TODO("Not yet implemented")
    }
}