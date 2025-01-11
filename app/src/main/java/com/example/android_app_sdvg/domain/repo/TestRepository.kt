package com.example.android_app_sdvg.domain.repo

import com.example.android_app_sdvg.presentation.model.test.TestItem
import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 */
interface TestRepository {
    suspend fun getStatusTest(): Flow<String>

    suspend fun getTest(): List<TestItem>

    suspend fun getTestResult(): Flow<Int>

    suspend fun passTest()

    suspend fun saveTestResult(result: Int)
}