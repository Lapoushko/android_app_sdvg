package com.example.android_app_sdvg.domain.entity.category

/**
 * @author Lapoushko
 * Категория
 */
enum class Category(val naming: String) {
    STANDARD(naming = "Стандартная"),
    OTHER(naming = "Другая")
}

fun String.getCategory() = Category.entries.find { it.naming == this }