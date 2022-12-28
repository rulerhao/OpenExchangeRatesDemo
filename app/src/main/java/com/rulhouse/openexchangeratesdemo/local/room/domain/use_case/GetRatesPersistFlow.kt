package com.rulhouse.openexchangeratesdemo.local.room.domain.use_case


import com.rulhouse.openexchangeratesdemo.local.room.domain.repository.RatesPersistRepository
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.Rate
import kotlinx.coroutines.flow.Flow

class GetRatesPersistFlow (
    private val repository: RatesPersistRepository
) {
    suspend operator fun invoke(): Flow<List<Rate>> {
        return repository.getRatesPersistFlow()
    }
}