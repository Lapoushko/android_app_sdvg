package com.example.android_app_sdvg.domain.usecase.test

import com.example.android_app_sdvg.domain.repo.TestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface SubscribeResultTest {
    suspend fun getTestResult(): Flow<Int>

    suspend fun saveTestResult(result: Int)
}

class SubscribeResultTestImpl @Inject constructor(
    private val repo: TestRepository
) : SubscribeResultTest{
    override suspend fun getTestResult(): Flow<Int> {
        return repo.getTestResult()
    }

    override suspend fun saveTestResult(result: Int) {
        repo.saveTestResult(result)
    }

}