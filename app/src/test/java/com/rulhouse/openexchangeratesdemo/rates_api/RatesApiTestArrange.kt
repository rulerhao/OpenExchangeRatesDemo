package com.rulhouse.openexchangeratesdemo.rates_api

import com.rulhouse.openexchangeratesdemo.remote.rates.impl.RatesApiImpl
import com.rulhouse.openexchangeratesdemo.remote.rates.service.RatesApiService
import com.rulhouse.openexchangeratesdemo.remote.rates.use_cases.GetRates
import com.rulhouse.openexchangeratesdemo.remote.rates.use_cases.RatesApiUseCases
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RatesApiTestArrange {

    private val BASE_URL = "https://openexchangerates.org/"

    private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    private val apiService = retrofit.create(RatesApiService::class.java)

    private val repository = RatesApiImpl(apiService)

    val ratesApiUseCases = RatesApiUseCases(
        getRates = GetRates(repository)
    )

    val correctTest = Pair("ce7d15a38b444f7e93aeeff7216bcdde", null)
    val invalidBaseTest = Pair("ce7d15a38b444f7e93aeeff7216bcdde", "JPY")
    val invalidAppIdTest = Pair("ce7d15a38b444f7e93aeeff7216bcdd", "JPY")
    val missingAppIdTest = Pair(null, "JPY")
}