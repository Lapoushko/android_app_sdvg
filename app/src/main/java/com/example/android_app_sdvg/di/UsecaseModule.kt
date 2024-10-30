package com.example.android_app_sdvg.di

import com.example.android_app_sdvg.domain.repo.TaskRepository
import com.example.android_app_sdvg.domain.usecase.SubscribeInsertTaskUseCase
import com.example.android_app_sdvg.domain.usecase.SubscribeInsertTaskUseCaseImpl
import com.example.android_app_sdvg.domain.usecase.SubscribeTasksUseCase
import com.example.android_app_sdvg.domain.usecase.SubscribeTasksUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Lapoushko
 * di всех юзкейсов
 */
@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {

    @Singleton
    @Provides
    fun provideSubscribeTasksUseCase(
        repository: TaskRepository
    ): SubscribeTasksUseCase {
        return SubscribeTasksUseCaseImpl(repository = repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeInsertTaskUseCase(
        repository: TaskRepository
    ) : SubscribeInsertTaskUseCase{
        return SubscribeInsertTaskUseCaseImpl(repository = repository)
    }
}