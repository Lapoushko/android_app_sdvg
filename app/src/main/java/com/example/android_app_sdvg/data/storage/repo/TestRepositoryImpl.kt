package com.example.android_app_sdvg.data.storage.repo

import ConstantsPreferences
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.android_app_sdvg.data.storage.mock.MockTest
import com.example.android_app_sdvg.domain.repo.TestRepository
import com.example.android_app_sdvg.presentation.model.test.StatusTest
import com.example.android_app_sdvg.presentation.model.test.TestItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Lapoushko
 */
class TestRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : TestRepository {
    private val test = MockTest().test

    override suspend fun getStatusTest(): Flow<String> {
        return context.testStore.data.map { pref ->
            pref[StatusKeys.STATUS_TEST] ?: ""
        }
    }

    override suspend fun passTest() {
        context.testStore.edit { pref ->
            pref[StatusKeys.STATUS_TEST] = StatusTest.COMPLETED.naming
        }
    }

    override suspend fun getTest(): List<TestItem> {
        return test
    }

    override suspend fun saveTestResult(result: Int) {
        context.testStore.edit { pref ->
            pref[StatusKeys.RESULT_TEST] = result
        }
    }

    override suspend fun getTestResult(): Flow<Int> {
        return context.testStore.data.map { pref ->
            pref[StatusKeys.RESULT_TEST] ?: 0
        }
    }
}

private val Context.testStore: DataStore<Preferences> by preferencesDataStore(ConstantsPreferences.TEST_NAME)

private object StatusKeys {
    val STATUS_TEST = stringPreferencesKey(ConstantsPreferences.STATUS_TEST)
    val RESULT_TEST = intPreferencesKey(ConstantsPreferences.RESULT_TEST)
}