package com.example.android_app_sdvg.presentation.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowCircleLeft
import androidx.compose.material.icons.outlined.ArrowCircleRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.presentation.component.CustomTopAppBar
import com.example.android_app_sdvg.presentation.component.TopBarForEditing
import com.example.android_app_sdvg.presentation.model.test.Answers

/**
 * @author Lapoushko
 */
@Composable
fun TestingScreen(
    viewModel: TestingScreenViewModel = hiltViewModel(),
    onToBack: () -> Unit
) {
    val state = viewModel.state
    val indexCurrentQuestion = state.indexCurrentQuestion
    val currentQuestion = state.tests[indexCurrentQuestion]
    val startAnswer = currentQuestion.answer

    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            CustomTopAppBar(stringResource(R.string.testing))
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    innerPadding
                )
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TopBarForEditing(
                onToBack = { onToBack() },
                label = "",
            )
            Question(
                text = "${indexCurrentQuestion + 1}. ${currentQuestion.text}",
                onClick = {
                    viewModel.updateAnswer(it, test = currentQuestion)
                },
                onSave = { viewModel.save { onToBack() } },
                onSwitchDirections = { viewModel.switchQuestion(it) },
                currentIndex = state.indexCurrentQuestion,
                startAnswer = startAnswer,
                maxIndex = state.tests.lastIndex
            )
        }
    }
}

@Composable
private fun Question(
    text: String,
    onClick: (Answers) -> Unit,
    onSave: () -> Unit,
    onSwitchDirections: (Int) -> Unit,
    currentIndex: Int,
    startAnswer: Answers?,
    maxIndex: Int
) {
    QuestionText(text = text)
    SelectableQuestions(idQuestion = currentIndex,onClick, startAnswer = startAnswer)
    DirectionsButtons(
        onClick = { onSwitchDirections(it) },
        currentIndex = currentIndex,
        maxIndex
    )
    if (currentIndex == maxIndex) {
        ButtonEndTest { onSave() }
    }
}

@Composable
private fun QuestionText(text: String) {
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            text = text,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SelectableQuestions(
    idQuestion: Int,
    onClick: (Answers) -> Unit,
    startAnswer: Answers? = null
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(startAnswer) }

    LaunchedEffect(idQuestion) {
        onOptionSelected(startAnswer)
    }

    Column {
        Answers.entries.forEach { answer ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (answer == selectedOption),
                        onClick = {
                            onOptionSelected(answer)
                            onClick(answer)
                        }
                    )
            ) {
                RadioButton(
                    selected = (selectedOption == answer),
                    onClick = {
                        onOptionSelected(answer)
                        onClick(answer)
                    }
                )
                Text(
                    text = answer.naming,
                    modifier = Modifier.padding(4.dp),
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
private fun DirectionsButtons(
    onClick: (Int) -> Unit,
    currentIndex: Int,
    maxIndex: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ArrowButton(isLeft = true, onClick = { onClick(it) }, isEnabled = currentIndex != 0)
        ArrowButton(
            isLeft = false,
            onClick = { onClick(it) },
            isEnabled = (currentIndex != maxIndex)
        )
    }
}

@Composable
private fun ArrowButton(
    isLeft: Boolean = true,
    onClick: (Int) -> Unit,
    isEnabled: Boolean = true
) {
    val size = 50.dp
    val imageVector =
        if (isLeft) Icons.Outlined.ArrowCircleLeft else Icons.Outlined.ArrowCircleRight
    val param = if (isLeft) -1 else 1
    IconButton(
        onClick = { onClick(param) },
        modifier = Modifier
            .size(size),
        enabled = isEnabled
    ) {
        Icon(
            modifier = Modifier.size(size),
            imageVector = imageVector,
            contentDescription = null
        )
    }
}

@Composable
private fun ButtonEndTest(
    onClick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(onClick = onClick) {
            Text(text = "Отправить")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestingScreenPreview() {
    TestingScreen( onToBack = {})
}