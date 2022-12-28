package com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.data.RatesPreferencesDataStoreFactory
import com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.repository.RatesPreferencesDataStoreRepository
import com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.impl.RatesPreferencesDataStoreImpl
import com.rulhouse.openexchangeratesdemo.datastore.RatesPreferencesProto
import com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.use_cases.GetRatesPreferencesDataStoreFlow
import com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.use_cases.RatesPreferencesDataStoreUseCases
import com.rulhouse.openexchangeratesdemo.local.preferences.user_preferences.use_cases.UpdateLastFetchTimestamp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RatesPreferencesDataStoreModule {

    @Singleton
    @Provides
    fun provideRatesPreferencesProtoDataStore(@ApplicationContext appContext: Context): DataStore<RatesPreferencesProto> {
        return RatesPreferencesDataStoreFactory().create(appContext)
    }

    @Provides
    @Singleton
    fun provideRatesPreferencesDataStoreRepository(dataStore: DataStore<RatesPreferencesProto>): RatesPreferencesDataStoreRepository {
        return RatesPreferencesDataStoreImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideRatesPreferencesDataStoreUseCases(repository: RatesPreferencesDataStoreRepository): RatesPreferencesDataStoreUseCases {
        return RatesPreferencesDataStoreUseCases(
            getRatesPreferencesDataStoreFlow = GetRatesPreferencesDataStoreFlow(repository),
            updateLastFetchTimestamp = UpdateLastFetchTimestamp(repository)
        )
    }
}