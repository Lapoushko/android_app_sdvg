package com.example.android_app_sdvg.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android_app_sdvg.data.storage.util.ConstantsDatabase
import com.example.android_app_sdvg.domain.entity.category.Category
import com.example.android_app_sdvg.domain.entity.prioriry.Priority

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
    @ColumnInfo("dateStart") val dateStart: Long?,
    @ColumnInfo("dateEnd") val dateEnd: Long?,
    @ColumnInfo("timer") val timer: Long?,
    @ColumnInfo("capacity") val capacity: Long?,
    @ColumnInfo("periodicity") val periodicity: Int?,
    @ColumnInfo("priority") val priority: Priority?,
    @ColumnInfo("category") val category: Category?
)