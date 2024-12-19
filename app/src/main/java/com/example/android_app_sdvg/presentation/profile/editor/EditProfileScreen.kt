package com.example.android_app_sdvg.presentation.profile.editor

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.android_app_sdvg.R
import com.example.android_app_sdvg.domain.entity.profile.Sex
import com.example.android_app_sdvg.presentation.component.CustomTopAppBar
import com.example.android_app_sdvg.presentation.component.DatePickerModal
import com.example.android_app_sdvg.presentation.component.DropdownMenuBox
import com.example.android_app_sdvg.presentation.component.TextFieldOption
import com.example.android_app_sdvg.presentation.component.TopBarForEditing
import com.example.android_app_sdvg.presentation.extension.toDateString
import com.example.android_app_sdvg.presentation.model.profile.ProfileItem

/**
 * @author Lapoushko
 */
@Composable
fun EditProfileScreen(
    onToBack: () -> Unit,
    profile: ProfileItem,
    viewModel: EditProfileScreenViewModel = hiltViewModel()
) {
    val profileState = viewModel.profileState
    val context = LocalContext.current

    val pickMedia: ActivityResultLauncher<PickVisualMediaRequest> =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            viewModel.onImageSelected(uri)
        }

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (!isGranted) {
                val dialog = AlertDialog.Builder(context)
                    .setMessage("Ну, так не пойдет...")
                    .setCancelable(false)
                    .setPositiveButton("OK") { _, _ ->
                        onToBack()
                    }

                dialog.show()
            }
            viewModel.onPermissionClosed()
        }

    viewModel.updateProfile(profile)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(stringResource(R.string.edit_profile))
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            TopBarForEditing(
                onToBack = onToBack,
                save = { viewModel.save { onToBack() } },
                label = ""
            )

            AsyncImage(
                model = Uri.parse(profileState.photo?.text ?: ""),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(128.dp)
                    .clip(CircleShape)
                    .clickable { viewModel.onToggleGallery() },
                error = painterResource(R.drawable.person)
            )

            TextFieldOption(
                text = profileState.name?.text ?: "",
                onTextChange = {
                    viewModel.updateName(it)
                },
                label = stringResource(id = R.string.edit_profile_name),
                isError = !profileState.name?.error?.naming.isNullOrEmpty(),
                error = profileState.name?.error?.naming ?: ""
            )

            TextFieldOption(
                text = profileState.email?.text ?: "",
                onTextChange = {
                    viewModel.updateEmail(it)
                },
                label = stringResource(id = R.string.edit_profile_email),
                isError = !profileState.email?.error?.naming.isNullOrEmpty(),
                error = profileState.email?.error?.naming ?: ""
            )

            DropdownMenuBox(
                items = Sex.entries.map { it.naming },
                label = "Пол",
                onTextChange = { viewModel.updateSex(it) },
                isError = !profileState.sex?.error?.naming.isNullOrEmpty(),
                error = profileState.sex?.error?.naming ?: ""
            )
            TextFieldOption(
                text = profileState.dateBirthday?.text ?: "",
                onTextChange = {
                    viewModel.updateDateBirthday(it)
                },
                label = stringResource(id = R.string.edit_profile_dateBirthday),
                isError = !profileState.dateBirthday?.error?.naming.isNullOrEmpty(),
                error = profileState.dateBirthday?.error?.naming ?: "",
                trailingImage = Icons.Filled.DateRange,
                trailingClick = { viewModel.toggleDatePicker() }
            )
        }
        if (profileState.isNeedToShowDatePicker) {
            DatePickerModal(
                onDateSelected = {
                    viewModel.updateDateBirthday(it?.toDateString(isRu = false) ?: "")
                    viewModel.toggleDatePicker()
                },
                onDismiss = { viewModel.toggleDatePicker() }
            )
        }
        if (profileState.isNeedToShowPermission) {
            LaunchedEffect(Unit) {
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q &&
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissionLauncher.launch(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                }
            }
        }

        if (profileState.isNeedToShowSelect) {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            viewModel.onToggleGallery()
        }
    }
}