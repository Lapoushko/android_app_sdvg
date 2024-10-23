package com.example.android_app_sdvg.presentation.util

import android.os.Bundle
import androidx.navigation.NavType

/**
 * @author Lapoushko
 */
class PrimitiveNavType<T : Any>(private val clazz: Class<T>) : NavType<T>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): T {
        return when (clazz) {
            Long::class.java -> bundle.getLong(key) as T
            Int::class.java -> bundle.getInt(key) as T
            Float::class.java -> bundle.getFloat(key) as T
            Double::class.java -> bundle.getDouble(key) as T
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    override fun parseValue(value: String): T {
        return when (clazz) {
            Long::class.java -> value.toLong() as T
            Int::class.java -> value.toInt() as T
            Float::class.java -> value.toFloat() as T
            Double::class.java -> value.toDouble() as T
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        when (clazz) {
            Long::class.java -> bundle.putLong(key, value as Long)
            Int::class.java -> bundle.putInt(key, value as Int)
            Float::class.java -> bundle.putFloat(key, value as Float)
            Double::class.java -> bundle.putDouble(key, value as Double)
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    override fun serializeAsValue(value: T): String {
        return value.toString()
    }

    override val name: String get() = clazz.name
}