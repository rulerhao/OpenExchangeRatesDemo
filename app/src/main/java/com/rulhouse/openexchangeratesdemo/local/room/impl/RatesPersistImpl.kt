package com.rulhouse.openexchangeratesdemo.local.room.impl

import com.rulhouse.openexchangeratesdemo.local.room.data_source.RatesPersistDao
import com.rulhouse.openexchangeratesdemo.local.room.domain.repository.RatesPersistRepository
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.Rate
import kotlinx.coroutines.flow.Flow

class RatesPersistImpl (
    private val dao: RatesPersistDao
) : RatesPersistRepository {

    override suspend fun getRatesPersistFlow(): Flow<List<Rate>> {
        return dao.getRatesFlow()
    }

    override suspend fun insertRatesPersist(rates: List<Rate>) {
        dao.insertRates(rates)
    }

}