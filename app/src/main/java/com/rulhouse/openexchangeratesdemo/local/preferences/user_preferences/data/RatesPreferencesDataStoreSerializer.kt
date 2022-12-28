package com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.rulhouse.openexchangeratesdemo.datastore.RatesPreferencesProto
import java.io.InputStream
import java.io.OutputStream

object RatesPreferencesDataStoreSerializer : Serializer<RatesPreferencesProto> {
    override val defaultValue: RatesPreferencesProto = RatesPreferencesProto.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): RatesPreferencesProto {
        try {
            return RatesPreferencesProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: RatesPreferencesProto, output: OutputStream) = t.writeTo(output)
}