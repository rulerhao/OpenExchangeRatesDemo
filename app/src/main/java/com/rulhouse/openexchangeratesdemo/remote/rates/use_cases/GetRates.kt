package com.rulhouse.openexchangeratesdemo.remote.rates.use_cases

import com.rulhouse.openexchangeratesdemo.remote.rates.dto.CurrencyRate
import com.rulhouse.openexchangeratesdemo.remote.rates.error.StatusTypes
import com.rulhouse.openexchangeratesdemo.remote.rates.repository.RatesApiRepository
import com.rulhouse.openexchangeratesdemo.remote.response.BaseResult
import kotlinx.coroutines.flow.Flow

class GetRates (
    private val repository: RatesApiRepository
) {
    suspend operator fun invoke(
        appId: String?,
        base: String?
    ): Flow<BaseResult<List<CurrencyRate>, StatusTypes>> {
        return repository.getRates(appId, base)
    }
}