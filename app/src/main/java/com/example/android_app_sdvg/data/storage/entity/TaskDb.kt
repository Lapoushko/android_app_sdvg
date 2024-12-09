package com.example.android_app_sdvg.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.android_app_sdvg.data.storage.util.ConstantsDatabase
import com.example.android_app_sdvg.data.storage.util.CustomTypeConverters
import com.example.android_app_sdvg.domain.entity.category.Category
import com.example.android_app_sdvg.domain.entity.prioriry.Priority
import com.example.android_app_sdvg.domain.entity.task.Dates
import com.example.android_app_sdvg.domain.entity.task.TaskStatus

/**
 * @author Lapoushko
 * Задачи db
 * @param id айди задачи
 * @param name имя задачи
 * @param description описание задачи
 * @param timer Длительность выполнения задачи
 * @param capacity объём задачи
 * @param periodicity периодичность появления задачи
 * @param priority приоритет выполнения задачи
 * @param category категория задачи
 */
@Entity(tableName = ConstantsDatabase.NAME_TABLE_GROUPS)
data class TaskDb(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo("name") val name: String?,
    @ColumnInfo("description") val description: String?,
    @TypeConverters(CustomTypeConverters::class)
    @ColumnInfo("dates") val dates: Dates?,
    @ColumnInfo("timer") val timer: Long?,
    @ColumnInfo("capacity") val capacity: Int?,
    @ColumnInfo("periodicity") val periodicity: Int?,
    @ColumnInfo("priority") val priority: Priority?,
    @ColumnInfo("category") val category: Category?,
    @ColumnInfo("taskStatus") val taskStatus : TaskStatus? = null
)