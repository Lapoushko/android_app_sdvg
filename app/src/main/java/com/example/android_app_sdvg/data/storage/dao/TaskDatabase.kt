package com.example.android_app_sdvg.data.storage.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_app_sdvg.data.storage.entity.TaskDb
import com.example.android_app_sdvg.data.storage.util.ConstantsDatabase
import com.example.android_app_sdvg.data.storage.util.CustomTypeConverters

/**
 * @author Lapoushko
 */
@Database(
    entities = [
        TaskDb::class
    ],
    version = ConstantsDatabase.VERSION_DATABASE
)
@TypeConverters(CustomTypeConverters::class)
abstract class TaskDatabase: RoomDatabase(){
    abstract fun TaskDao(): TaskDao
}

