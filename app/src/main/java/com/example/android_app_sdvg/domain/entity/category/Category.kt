package com.example.android_app_sdvg.domain.entity.category

/**
 * @author Lapoushko
 * Категория
 */
enum class Category(val naming: String, val value: Int) {
    STANDARD(naming = "Стандартная",1),
    OTHER(naming = "Другая",0)
}

fun String.getCategory() = Category.entries.find { it.naming == this }