package com.example.android_app_sdvg.presentation.util

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.example.android_app_sdvg.presentation.model.profile.ProfileItem
import com.example.android_app_sdvg.presentation.model.task.TaskItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * @author Lapoushko
 * Кастомный NavType для навигации сложного объекта FilmItem
 * @param clazz нужный класс
 * @param serializer сериализатор
 */
val CustomNavTypeTask = object : NavType<TaskItem>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): TaskItem {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, TaskItem::class.java) as TaskItem
        } else {
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

val CustomNavTypeProfile = object : NavType<ProfileItem>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ProfileItem {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, ProfileItem::class.java) as ProfileItem
        } else {
            bundle.getParcelable<ProfileItem>(key) as ProfileItem
        }
    }

    override fun parseValue(value: String): ProfileItem {
        return Json.decodeFromString<ProfileItem>(value)
    }

    override fun put(bundle: Bundle, key: String, value: ProfileItem) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: ProfileItem): String {
        return Json.encodeToString<ProfileItem>(
            value.copy(
                photo = Uri.parse(URLEncoder.encode(value.photo.toString(), StandardCharsets.UTF_8.toString())),
            )
        )
    }

    override val name: String = ProfileItem::class.java.name
}