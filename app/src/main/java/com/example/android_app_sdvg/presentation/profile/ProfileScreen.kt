package com.example.android_app_sdvg.presentation.profile

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.presentation.component.CustomTopAppBar
import com.example.android_app_sdvg.presentation.component.UnderLine
import com.example.android_app_sdvg.presentation.model.profile.ProfileItem
import com.example.android_app_sdvg.presentation.model.test.StatusTest

/**
 * @author Lapoushko
 *
 * Экран личного кабинета
 * @param profileScreenHandler методы личного кабинета
 */
@Composable
fun ProfileScreen(
    profileScreenHandler: ProfileScreenHandler,
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val profileState = profileScreenViewModel.profileState
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            CustomTopAppBar(stringResource(R.string.personal_account))
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            ProfileInfo(
                name = profileState.name,
                email = profileState.email,
                photo = profileState.photo,
                onToEdit = {
                    profileScreenHandler.onToEdit(
                        ProfileItem(
                            name = profileState.name,
                            email = profileState.email,
                            sex = profileState.sex,
                            dateBirthday = profileState.dateBirthday,
                            photo = profileState.photo
                        )
                    )
                },
            )
            Spacer(modifier = Modifier.height(16.dp))



            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 16.dp)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Result(
                    profileState.resultLastTest
                )

                ResultText(
                    profileState.infoByResultTest.text,
                    profileState.infoByResultTest.color
                )
                OpenTest(
                    onOpen = {
                        profileScreenHandler.onToTest()
                    },
                    statusTest = profileState.statusTest
                )
            }
        }
    }
}

@Composable
private fun ProfileInfo(
    name: String,
    email: String,
    photo: Uri,
    onToEdit: () -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = photo,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp)),
                error = painterResource(R.drawable.person)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(PaddingValues(bottom = 20.dp))
            ) {
                ShortDescriptionPerson(
                    title = "Имя: ",
                    text = name.take(30)
                )
                ShortDescriptionPerson(
                    title = "Почта: ",
                    text = email.take(30)
                )
            }
            IconButton(
                modifier = Modifier
                    .align(Alignment.Bottom),
                onClick = { onToEdit() }
            ) {
                Icon(
                    imageVector = Icons.Filled.EditNote,
                    contentDescription = null
                )
            }
        }
        UnderLine()
    }
}

@Composable
private fun ShortDescriptionPerson(
    title: String,
    text: String
) {
    Column {
        Text(text = title)
    }
    Column {
        Text(
            text = text.ifEmpty { "Нет данных" },
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
private fun Result(
    result: String
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "${stringResource(R.string.profile_screen_result)} $result",
        textAlign = TextAlign.Center,
        fontSize = 26.sp
    )
}


@Composable
private fun OpenTest(
    onOpen: () -> Unit,
    statusTest: StatusTest = StatusTest.NOT_COMPLETED,
) {
    val buttonText = if (statusTest == StatusTest.COMPLETED) "История" else "ТЕСТ!"
//    Row(
//        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
//    ) {
//        Column {
//            Button(
//                onClick = onOpen,
//                modifier = Modifier
//                    .padding(PaddingValues(bottom = 5.dp)),
//            ) {
//                Text(
//                    text = buttonText,
//                    fontSize = 26.sp,
//                    textAlign = TextAlign.Center,
//                )
//            }
//            if (statusTest == StatusTest.COMPLETED) {
//                Text(
//                    text = "Следующий тест через 1д. 20ч.",
//                    color = Color.Gray,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//        }
//    }
    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onOpen,
                modifier = Modifier
                    .padding(PaddingValues(bottom = 5.dp)),
            ) {
                Text(
                    text = buttonText,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        if (statusTest == StatusTest.COMPLETED) {
            Text(
                text = "Следующий тест через 1д. 20ч.",
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ResultText(
    text: String,
    color: Color
) {
    Text(
        fontSize = 26.sp,
        text = text,
        color = color,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun PersonalAccountScreenPreview() {
    ProfileScreen(
        profileScreenHandler = ProfileScreenHandler(
            navController = rememberNavController()
        )
    )
}