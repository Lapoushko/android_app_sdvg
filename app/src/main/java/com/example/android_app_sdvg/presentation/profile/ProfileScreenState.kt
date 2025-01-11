package com.example.android_app_sdvg.presentation.profile

import android.net.Uri
import androidx.compose.ui.graphics.Color
import com.example.android_app_sdvg.presentation.model.test.StatusTest

/**
 * @author Lapoushko
 */
interface ProfileScreenState {
    val name: String
    val email: String
    val photo: Uri
    val sex: String
    val dateBirthday: String
    val resultLastTest: String
    val infoByResultTest: TestResults
    val recommendations: List<String>
    val statusTest: StatusTest
    val remainingOpeningTime: String
}

enum class TestResults(val text: String = "", val color: Color = Color.White){
    LOW("20-40 баллов: Низкая вероятность наличия СДВГ.", Color.Green),
    MEDIUM("41-60 баллов: Умеренная вероятность наличия СДВГ.", Color.Yellow),
    HIGH("61 и более баллов: Высокая вероятность наличия СДВГ.", Color.Red)
}