package com.example.android_app_sdvg.data.storage.util

import android.util.Log
import androidx.datastore.core.Serializer
import com.example.android_app_sdvg.data.storage.entity.ProfileDb
import com.example.android_app_sdvg.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

/**
 * @author Lapoushko
 */
object ProtoDatastoreSerializer : Serializer<ProfileDb> {
    override val defaultValue: ProfileDb
        get() = ProfileDb()

    override suspend fun readFrom(input: InputStream): ProfileDb {
        return try {
            Json.decodeFromString(
                deserializer = ProfileDb.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException){
            Log.e(Constants.LOG_KEY, e.toString())
            ProfileDb()
        }
    }

    override suspend fun writeTo(t: ProfileDb, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = ProfileDb.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}