package com.example.android_app_sdvg.domain.usecase.test

import com.example.android_app_sdvg.domain.repo.TestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface SubscribeStatusTest{
    suspend fun getStatusTest() : Flow<String>

    suspend fun passTest()
}

class SubscribeStatusTestImpl @Inject constructor(
    private val repo: TestRepository
) : SubscribeStatusTest{
    override suspend fun getStatusTest(): Flow<String> {
        return repo.getStatusTest()
    }

    override suspend fun passTest() {
        repo.passTest()
    }
}