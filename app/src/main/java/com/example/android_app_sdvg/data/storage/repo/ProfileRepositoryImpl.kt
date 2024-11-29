package com.example.android_app_sdvg.data.storage.repo

import android.content.Context
import androidx.datastore.dataStore
import com.example.android_app_sdvg.data.storage.mapper.profile.ProfileMapperDB
import com.example.android_app_sdvg.data.storage.util.ProtoDatastoreSerializer
import com.example.android_app_sdvg.domain.entity.profile.Profile
import com.example.android_app_sdvg.domain.repo.ProfileRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Lapoushko
 */
class ProfileRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileMapperDB: ProfileMapperDB
) : ProfileRepository {
    override suspend fun saveProfile(profile: Profile) {
        context.protoDataStore.updateData { data ->
            data.copy(
                name = profile.name,
                email = profile.email,
                sex = profile.sex,
                photo = profile.photo,
                dateBirthday = profile.dateBirthday
            )
        }
    }

    override fun getProfile(): Flow<Profile> {
        return context.protoDataStore.data.map { data ->
            profileMapperDB.toDomain(data)
        }
    }
}

private val Context.protoDataStore by dataStore("profile.json", ProtoDatastoreSerializer)