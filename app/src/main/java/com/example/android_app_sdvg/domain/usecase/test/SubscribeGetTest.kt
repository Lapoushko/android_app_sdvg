package com.example.android_app_sdvg.domain.usecase.test

import com.example.android_app_sdvg.domain.repo.TestRepository
import com.example.android_app_sdvg.presentation.model.test.TestItem
import javax.inject.Inject

/**
 * @author Lapoushko
 */
interface SubscribeGetTest{
    suspend fun getTest() : List<TestItem>
}

class SubscribeGetTestImpl @Inject constructor(private val repo: TestRepository) : SubscribeGetTest{
    override suspend fun getTest() : List<TestItem> {
        return repo.getTest()
    }
}