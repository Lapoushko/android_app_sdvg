package com.example.android_app_sdvg.presentation.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class GenericSerializer<T : Any>(
    private val serializeFn: (T) -> String,
    private val deserializeFn: (String) -> T
) : KSerializer<T> {

    override val descriptor = PrimitiveSerialDescriptor("Generic", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: T) {
        encoder.encodeString(serializeFn(value))
    }

    override fun deserialize(decoder: Decoder): T {
        return deserializeFn(decoder.decodeString())
    }
}