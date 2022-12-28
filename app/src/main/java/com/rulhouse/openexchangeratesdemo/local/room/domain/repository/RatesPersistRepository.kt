package com.rulhouse.openexchangeratesdemo.local.room.domain.repository

import com.rulhouse.openexchangeratesdemo.remote.rates.dto.Rate
import kotlinx.coroutines.flow.Flow

interface RatesPersistRepository {

    suspend fun getRatesPersistFlow(): Flow<List<Rate>>

    suspend fun insertRatesPersist(rates: List<Rate>)

}