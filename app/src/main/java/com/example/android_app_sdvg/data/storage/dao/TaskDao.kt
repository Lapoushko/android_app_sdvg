package com.example.android_app_sdvg.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.android_app_sdvg.data.storage.entity.TaskDb
import com.example.android_app_sdvg.domain.entity.task.Dates

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
    @Query("DELETE FROM tasks WHERE name = :name AND description = :description AND dates = :dates")
    suspend fun deleteTask(name: String, description: String, dates: Dates)

    /**
     * обновить задачу
     * @param taskDb задача из бд
     */
//    @Query("UPDATE tasks SET name = :name, description = :description, dates = :dates WHERE id = :id")
    @Update
    suspend fun updateTask(taskDb: TaskDb)
}