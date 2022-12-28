package com.rulhouse.openexchangeratesdemo.remote.rates.di

import com.rulhouse.openexchangeratesdemo.remote.rates.impl.RatesApiImpl
import com.rulhouse.openexchangeratesdemo.remote.rates.repository.RatesApiRepository
import com.rulhouse.openexchangeratesdemo.remote.rates.service.RatesApiService
import com.rulhouse.openexchangeratesdemo.remote.rates.use_cases.RatesApiUseCases
import com.rulhouse.openexchangeratesdemo.remote.rates.use_cases.GetRates
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RatesApiModule {
    private const val BASE_URL = "https://openexchangerates.org/"

    val appId = "ce7d15a38b444f7e93aeeff7216bcdde"
    val base: String? = null

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideRedditApiService(retrofit: Retrofit): RatesApiService {
        return retrofit.create(RatesApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesRepository(apiService: RatesApiService): RatesApiRepository {
        return RatesApiImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideRedditApiUseCases(repository: RatesApiRepository): RatesApiUseCases {
        return RatesApiUseCases(
            getRates = GetRates(repository)
        )
    }
}