package com.rulhouse.openexchangeratesdemo.remote.air_pollution.service

import com.rulhouse.airpollution.model.remote.air_pollution.dto.AirPollutionInformation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesApiService {

    @GET("api/latest.json")
    suspend fun getRatesInformation(
        @Query("app_id") appId: String,
        @Query("base") base: String
    ): Response<AirPollutionInformation>

}