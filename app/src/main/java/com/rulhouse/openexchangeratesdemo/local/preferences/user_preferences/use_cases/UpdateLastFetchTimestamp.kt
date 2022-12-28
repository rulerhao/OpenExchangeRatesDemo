package com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.use_cases

import com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.repository.RatesPreferencesDataStoreRepository

class UpdateLastFetchTimestamp (
    private val repository: RatesPreferencesDataStoreRepository
) {
    suspend operator fun invoke(timeStamp: Long) {
        repository.updateLastFetchTimestamp(timeStamp)
    }
}