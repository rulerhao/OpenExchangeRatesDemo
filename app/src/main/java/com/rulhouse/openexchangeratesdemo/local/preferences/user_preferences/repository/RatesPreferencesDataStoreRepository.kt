package com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.repository

import com.rulhouse.openexchangeratesdemo.datastore.RatesPreferencesProto
import kotlinx.coroutines.flow.Flow

interface RatesPreferencesDataStoreRepository {
    fun getRatesPreferencesDataStoreFlow(): Flow<RatesPreferencesProto>

    suspend fun updateLastFetchTimestamp(timeStamp: Long)

    suspend fun fetchInitialPreferences(): RatesPreferencesProto
}