package com.example.android_app_sdvg.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_app_sdvg.data.storage.entity.TaskDb

/**
 * @author Lapoushko
 * Dao для задач
 */
@Dao
interface TaskDao {
    /**
     * Получить таски
     * @return flow таски
     */
    @Query("SELECT * FROM tasks")
    suspend fun getTasks(): List<TaskDb>

    /**
     * вставить в базу данных задачу
     * @param taskDb задача
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskDb: TaskDb)

    /**
     * удалить задачу
     */
    @Query("DELETE FROM tasks WHERE name = :name AND description = :description")
    suspend fun deleteTask(name: String, description: String)
}