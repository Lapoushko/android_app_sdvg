package com.example.android_app_sdvg.di

import android.content.Context
import androidx.room.Room
import com.example.android_app_sdvg.data.storage.dao.TaskDao
import com.example.android_app_sdvg.data.storage.dao.TaskDatabase
import com.example.android_app_sdvg.data.storage.util.ConstantsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Lapoushko
 * Модуль для DAO
 */
@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Singleton
    @Provides
    fun provideTaskDatabase(@ApplicationContext context: Context) : TaskDatabase =
        Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            ConstantsDatabase.NAME_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideTaskDao(appDatabase: TaskDatabase): TaskDao{
        return appDatabase.TaskDao()
    }
}