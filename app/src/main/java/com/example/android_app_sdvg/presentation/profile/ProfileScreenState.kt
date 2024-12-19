package com.example.android_app_sdvg.presentation.profile

import com.example.android_app_sdvg.presentation.model.test.StatusTest

/**
 * @author Lapoushko
 */
interface ProfileScreenState {
    val name: String
    val email: String
    val photo: String
    val sex: String
    val dateBirthday: String
    val resultLastTest: String
    val recommendations: List<String>
    val statusTest: StatusTest
    val remainingOpeningTime: String
}