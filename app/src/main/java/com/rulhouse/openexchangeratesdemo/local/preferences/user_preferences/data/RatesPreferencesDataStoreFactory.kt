package com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.datastore.migrations.SharedPreferencesView
import com.rulhouse.openexchangeratesdemo.datastore.RatesPreferencesProto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class RatesPreferencesDataStoreFactory {
    private val SHARED_PREFERENCES_NAME = "rates_preferences"

    private val USER_PREFERENCES_FILE_NAME = "rates_preferences_data_store.pb"

    fun create(context: Context): DataStore<RatesPreferencesProto> {
        return DataStoreFactory.create(
            serializer = RatesPreferencesDataStoreSerializer,
            produceFile = { context.dataStoreFile(USER_PREFERENCES_FILE_NAME) },
            corruptionHandler = null,
            migrations = listOf(
                SharedPreferencesMigration(
                    context = context,
                    sharedPreferencesName = SHARED_PREFERENCES_NAME
                ) { sharedPrefs: SharedPreferencesView, currentData: RatesPreferencesProto ->
                    // Define the mapping from SharedPreferences to UserPreferences
                    currentData.toBuilder().also {
                        it.lastFetchTimestamp = sharedPrefs.getLong("last_fetch_timestamp", 0)
                    }
                    currentData
                }
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )
    }
}