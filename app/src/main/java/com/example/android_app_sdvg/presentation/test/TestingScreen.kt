package com.example.android_app_sdvg.presentation.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.android_app_sdvg.presentation.model.test.Answers

/**
 * @author Lapoushko
 */
@Composable
fun TestingScreen(
    viewModel: TestingScreenViewModel = hiltViewModel(),
    onSave: () -> Unit
) {
    val state = viewModel.state

    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            CustomTopAppBar(stringResource(R.string.testing))
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(
                innerPadding
            ).fillMaxSize()
        ) {
            Column(
                modifier = Modifier.weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                state.tests.forEachIndexed { index, test ->
                    Question(
                        text = "${index + 1}. ${test.text}",
                        onClick = {
                            viewModel.updateAnswer(it, test)
                        }
                    )
                }
            }
            ButtonEndTest(onClick = {
                viewModel.save { onSave() }
            })
        }
    }
}

@Composable
private fun Question(
    text: String,
    onClick: (Answers) -> Unit
) {
    Column {
        QuestionText(text = text)
        SelectableQuestions { onClick(it) }
    }
}

@Composable
private fun QuestionText(text: String) {
    Text(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        text = text,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun SelectableQuestions(
    onClick: (Answers) -> Unit
) {
    val (selectedOption, onOptionSelected) = rememberSaveable { mutableStateOf<Answers?>(null) }
    Column {
        Answers.entries.forEach { answer ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (answer == selectedOption),
                        onClick = {
                            onClick(answer)
                            onOptionSelected(answer)
                        }
                    )
            ) {
                RadioButton(
                    selected = selectedOption == answer,
                    onClick = {
                        onClick(answer)
                        onOptionSelected(answer)
                    }
                )
                Text(
                    text = answer.naming,
                    modifier = Modifier.padding(4.dp),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
private fun ButtonEndTest(
    onClick: () -> Unit
) {
    Button(onClick = { onClick() }) {
        Text("Итого")
    }
}

@Preview(showBackground = true)
@Composable
fun TestingScreenPreview() {
    TestingScreen(onSave = {})
}