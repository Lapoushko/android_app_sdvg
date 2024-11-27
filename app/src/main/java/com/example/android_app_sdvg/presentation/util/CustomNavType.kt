package com.example.android_app_sdvg.presentation.util

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

/**
 * @author Lapoushko
 * Кастомный NavType для навигации сложного объекта FilmItem
 * @param clazz нужный класс
 * @param serializer сериализатор
 */
val CustomNavType = object : NavType<TaskItem>(isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): TaskItem {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            bundle.getParcelable(key, TaskItem::class.java) as TaskItem
        } else{
            bundle.getParcelable<TaskItem>(key) as TaskItem
        }
    }

    override fun parseValue(value: String): TaskItem {
        return Json.decodeFromString<TaskItem>(value)
    }

    override fun put(bundle: Bundle, key: String, value: TaskItem) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: TaskItem): String = Json.encodeToString<TaskItem>(value)

    override val name: String = TaskItem::class.java.name
}