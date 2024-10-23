package com.example.android_app_sdvg.di

import com.example.android_app_sdvg.data.storage.mapper.TaskDbToTaskMapper
import com.example.android_app_sdvg.data.storage.mapper.TaskDbToTaskMapperImpl
import com.example.android_app_sdvg.presentation.mapper.TaskToUiMapper
import com.example.android_app_sdvg.presentation.mapper.TaskToUiMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Lapoushko
 * модуль для мапперов
 */
@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Singleton
    @Provides
    fun provideTaskDbToTaskMapper(): TaskDbToTaskMapper{
        return TaskDbToTaskMapperImpl()
    }

    @Singleton
    @Provides
    fun provideTaskToUiMapper(): TaskToUiMapper{
        return TaskToUiMapperImpl()
    }
}