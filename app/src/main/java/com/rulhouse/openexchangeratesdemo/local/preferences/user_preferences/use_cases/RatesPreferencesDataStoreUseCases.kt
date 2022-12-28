package com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.use_cases

data class RatesPreferencesDataStoreUseCases (
    val getRatesPreferencesDataStoreFlow: GetRatesPreferencesDataStoreFlow,
    val updateLastFetchTimestamp: UpdateLastFetchTimestamp,
)