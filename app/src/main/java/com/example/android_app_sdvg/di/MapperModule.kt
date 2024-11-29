package com.example.android_app_sdvg.di

import com.example.android_app_sdvg.data.storage.mapper.profile.ProfileMapperDB
import com.example.android_app_sdvg.data.storage.mapper.profile.ProfileMapperDBImpl
import com.example.android_app_sdvg.data.storage.mapper.task.TaskDbToTaskMapper
import com.example.android_app_sdvg.data.storage.mapper.task.TaskDbToTaskMapperImpl
import com.example.android_app_sdvg.data.storage.mapper.task.TaskToTaskDbMapper
import com.example.android_app_sdvg.data.storage.mapper.task.TaskToTaskDbMapperImpl
import com.example.android_app_sdvg.presentation.mapper.profile.ProfileMapperUI
import com.example.android_app_sdvg.presentation.mapper.profile.ProfileMapperUIImpl
import com.example.android_app_sdvg.presentation.mapper.task.TaskDomainToUiMapper
import com.example.android_app_sdvg.presentation.mapper.task.TaskDomainToUiMapperImpl
import com.example.android_app_sdvg.presentation.mapper.task.TaskUiToTaskDomainMapper
import com.example.android_app_sdvg.presentation.mapper.task.TaskUiToTaskDomainMapperImpl
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
    fun provideTaskDbToTaskMapper(): TaskDbToTaskMapper {
        return TaskDbToTaskMapperImpl()
    }

    @Singleton
    @Provides
    fun provideTaskToUiMapper(): TaskDomainToUiMapper {
        return TaskDomainToUiMapperImpl()
    }

    @Singleton
    @Provides
    fun provideTaskUiToTaskMapper(): TaskUiToTaskDomainMapper {
        return TaskUiToTaskDomainMapperImpl()
    }

    @Singleton
    @Provides
    fun provideTaskToTaskDbMapper(): TaskToTaskDbMapper {
        return TaskToTaskDbMapperImpl()
    }

    @Singleton
    @Provides
    fun provideProfileMapperUI() : ProfileMapperUI{
        return ProfileMapperUIImpl()
    }

    @Singleton
    @Provides
    fun provideProfileMapperDB() : ProfileMapperDB {
        return ProfileMapperDBImpl()
    }
}