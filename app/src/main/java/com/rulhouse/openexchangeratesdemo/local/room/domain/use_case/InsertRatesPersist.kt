package com.rulhouse.openexchangeratesdemo.local.room.domain.use_case

import com.rulhouse.openexchangeratesdemo.local.room.domain.repository.RatesPersistRepository
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.Rate

class InsertRatesPersist (
    private val repository: RatesPersistRepository
) {
    suspend operator fun invoke(rates: List<Rate>) {
        repository.insertRatesPersist(rates)
    }
}