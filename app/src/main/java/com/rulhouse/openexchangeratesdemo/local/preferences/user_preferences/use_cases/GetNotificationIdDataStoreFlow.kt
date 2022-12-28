package com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.use_cases

import com.rulhouse.openexchangeratesdemo.datastore.RatesPreferencesProto
import com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.repository.RatesPreferencesDataStoreRepository
import kotlinx.coroutines.flow.Flow

class GetRatesPreferencesDataStoreFlow (
    private val repository: RatesPreferencesDataStoreRepository
) {
    operator fun invoke(): Flow<RatesPreferencesProto> {
        return repository.getRatesPreferencesDataStoreFlow()
    }
}