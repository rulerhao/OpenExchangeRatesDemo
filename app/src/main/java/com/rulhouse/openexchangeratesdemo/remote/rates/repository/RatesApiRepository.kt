package com.rulhouse.openexchangeratesdemo.remote.rates.repository

import com.rulhouse.openexchangeratesdemo.remote.response.BaseResult
import com.rulhouse.openexchangeratesdemo.remote.rates.error.StatusTypes
import kotlinx.coroutines.flow.Flow

interface RatesApiRepository {

    suspend fun getRates(
        appId: String?,
        base: String?
    ): Flow<BaseResult<List<Pair<String, Double>>, StatusTypes>>

}