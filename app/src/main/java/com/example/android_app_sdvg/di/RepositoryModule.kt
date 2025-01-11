package com.example.android_app_sdvg.di

import com.example.android_app_sdvg.data.storage.repo.ClickRepositoryImpl
import com.example.android_app_sdvg.data.storage.repo.ProfileRepositoryImpl
import com.example.android_app_sdvg.data.storage.repo.TaskRepositoryImpl
import com.example.android_app_sdvg.data.storage.repo.TestRepositoryImpl
import com.example.android_app_sdvg.domain.repo.ClickRepository
import com.example.android_app_sdvg.domain.repo.ProfileRepository
import com.example.android_app_sdvg.domain.repo.TaskRepository
import com.example.android_app_sdvg.domain.repo.TestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Lapoushko
 * di для репозиториев
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTaskRepository(
        taskRepository: TaskRepositoryImpl
    ): TaskRepository

    @Binds
    abstract fun bindClickRepository(
        clickRepository: ClickRepositoryImpl
    ): ClickRepository

    @Binds
    abstract fun bindProfileRepository(
        taskRepository: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    abstract fun bindTestRepository(
        testRepository: TestRepositoryImpl
    ) : TestRepository
}