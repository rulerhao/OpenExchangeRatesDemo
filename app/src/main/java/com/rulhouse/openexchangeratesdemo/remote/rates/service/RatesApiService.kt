package com.rulhouse.openexchangeratesdemo.remote.rates.service

import com.rulhouse.openexchangeratesdemo.remote.rates.dto.RatesApiInformation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesApiService {

    @GET("api/latest.json")
    suspend fun getRatesInformation(
        @Query("app_id") appId: String?,
        @Query("base") base: String?
    ): Response<RatesApiInformation>

}