package com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.impl

import androidx.datastore.core.DataStore
import com.rulhouse.openexchangeratesdemo.datastore.RatesPreferencesProto
import com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.repository.RatesPreferencesDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException

class RatesPreferencesDataStoreImpl(
    private val dataStore: DataStore<RatesPreferencesProto>
): RatesPreferencesDataStoreRepository {

    override fun getRatesPreferencesDataStoreFlow(): Flow<RatesPreferencesProto> {
        return dataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(RatesPreferencesProto.getDefaultInstance())
                } else {
                    throw exception
                }
            }
    }

    override suspend fun updateLastFetchTimestamp(timeStamp: Long) {
        dataStore.updateData { preferences ->
            preferences.toBuilder().setLastFetchTimestamp(timeStamp).build()
        }
    }

    override suspend fun fetchInitialPreferences() = dataStore.data.first()

}