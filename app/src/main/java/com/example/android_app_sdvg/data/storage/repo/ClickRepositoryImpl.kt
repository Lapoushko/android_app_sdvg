package com.example.android_app_sdvg.data.storage.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.android_app_sdvg.domain.repo.ClickRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Lapoushko
 */
class ClickRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ClickRepository{
    /**
     * сохранить сохранения
     */
    override suspend fun savePreferences(clicks: Int) {
        context.dataStore.edit { preference ->
            preference[PreferencesKeys.CLICKS] = clicks
        }
    }

    /**
     * получить сохранения
     */
    override fun getPreferences(): Flow<Int> {
        return context.dataStore.data.map { preference ->
            preference[PreferencesKeys.CLICKS] ?: 0
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(ConstantsPreferences.PREFERENCES_NAME)

private object PreferencesKeys {
    val CLICKS = intPreferencesKey(ConstantsPreferences.CLICKS)
}