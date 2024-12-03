package com.example.android_app_sdvg.presentation.profile

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
    val isClosedTest: Boolean
    val remainingOpeningTime: String
}