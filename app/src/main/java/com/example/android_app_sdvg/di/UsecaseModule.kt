package com.example.android_app_sdvg.di

import com.example.android_app_sdvg.domain.repo.ClickRepository
import com.example.android_app_sdvg.domain.repo.ProfileRepository
import com.example.android_app_sdvg.domain.repo.TaskRepository
import com.example.android_app_sdvg.domain.usecase.preferences.SubscribeClickUseCase
import com.example.android_app_sdvg.domain.usecase.preferences.SubscribeClickUseCaseImpl
import com.example.android_app_sdvg.domain.usecase.preferences.SubscribeGetClicksUseCase
import com.example.android_app_sdvg.domain.usecase.preferences.SubscribeGetClicksUseCaseImpl
import com.example.android_app_sdvg.domain.usecase.profile.SubscribeGetProfileUseCase
import com.example.android_app_sdvg.domain.usecase.profile.SubscribeGetProfileUseCaseImpl
import com.example.android_app_sdvg.domain.usecase.profile.SubscribeSaveProfileUseCase
import com.example.android_app_sdvg.domain.usecase.profile.SubscribeSaveProfileUseCaseImpl
import com.example.android_app_sdvg.domain.usecase.task.SubscribeDeleteTaskUseCase
import com.example.android_app_sdvg.domain.usecase.task.SubscribeDeleteTaskUseCaseImpl
import com.example.android_app_sdvg.domain.usecase.task.SubscribeEditTaskUseCase
import com.example.android_app_sdvg.domain.usecase.task.SubscribeEditTaskUseCaseImpl
import com.example.android_app_sdvg.domain.usecase.task.SubscribeInsertTaskUseCase
import com.example.android_app_sdvg.domain.usecase.task.SubscribeInsertTaskUseCaseImpl
import com.example.android_app_sdvg.domain.usecase.task.SubscribeTasksUseCase
import com.example.android_app_sdvg.domain.usecase.task.SubscribeTasksUseCaseImpl
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
    ): SubscribeInsertTaskUseCase {
        return SubscribeInsertTaskUseCaseImpl(repository = repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeDeleteTaskUseCase(
        repository: TaskRepository
    ): SubscribeDeleteTaskUseCase {
        return SubscribeDeleteTaskUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeClickUseCase(
        repository: ClickRepository
    ): SubscribeClickUseCase {
        return SubscribeClickUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeGetClicksUseCase(
        repository: ClickRepository
    ): SubscribeGetClicksUseCase {
        return SubscribeGetClicksUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeEditTaskUseCase(
        repository: TaskRepository
    ) : SubscribeEditTaskUseCase {
        return SubscribeEditTaskUseCaseImpl(repositoryImpl = repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeGetProfileUseCase(
        repository: ProfileRepository
    ) : SubscribeGetProfileUseCase{
        return SubscribeGetProfileUseCaseImpl(repository = repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeSaveProfileUseCase(
        repository: ProfileRepository
    ) : SubscribeSaveProfileUseCase {
        return SubscribeSaveProfileUseCaseImpl(repository = repository)
    }
}