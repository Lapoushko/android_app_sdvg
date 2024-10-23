package com.example.android_app_sdvg.di

import com.example.android_app_sdvg.data.storage.dao.TaskDao
import com.example.android_app_sdvg.data.storage.mapper.TaskDbToTaskMapper
import com.example.android_app_sdvg.data.storage.repo.TaskRepositoryImpl
import com.example.android_app_sdvg.domain.repo.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Lapoushko
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideTaskRepository(
        dao: TaskDao,
        mapper: TaskDbToTaskMapper
    ): TaskRepository {
        return TaskRepositoryImpl(dao = dao, mapper = mapper)
    }
}