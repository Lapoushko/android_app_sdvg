package com.example.android_app_sdvg.presentation.model.task

import android.os.Parcelable
import com.example.android_app_sdvg.domain.entity.task.Dates
import com.example.android_app_sdvg.presentation.extension.toLongDate
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 * время задачи
 */
@Serializable
@Parcelize
class DatesItem(
    val dateStart: Long,
    val dateEnd: Long
) : Parcelable

//fun DatesItem.toDate(): Dates {
//    return Dates(dateStart = this.dateStart.toLongDate(), dateEnd = this.dateEnd.toLongDate())
//}

fun DatesItem.toDate(): Dates {
    return Dates(dateStart = this.dateStart, dateEnd = this.dateEnd)
}