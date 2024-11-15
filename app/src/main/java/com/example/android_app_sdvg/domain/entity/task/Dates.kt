package com.example.android_app_sdvg.domain.entity.task

import com.example.android_app_sdvg.presentation.extension.toDateString
import com.example.android_app_sdvg.presentation.model.task.DatesItem

/**
 * @author Lapoushko
 */
class Dates(
    val dateStart: Long,
    val dateEnd: Long
)

//fun Dates.toDateItem(): DatesItem {
//    return DatesItem(
//        dateStart = this.dateStart.toDateString(),
//        dateEnd = this.dateEnd.toDateString()
//    )
//}

fun Dates.toDateItem(): DatesItem {
    return DatesItem(
        dateStart = this.dateStart,
        dateEnd = this.dateEnd
    )
}