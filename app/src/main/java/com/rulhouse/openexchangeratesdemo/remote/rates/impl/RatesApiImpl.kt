package com.rulhouse.openexchangeratesdemo.remote.rates.impl

import com.rulhouse.openexchangeratesdemo.remote.rates.dto.CurrencyRate
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.Rate
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.error_body.ErrorBodyParser
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.error_body.RatesApiErrorBody
import com.rulhouse.openexchangeratesdemo.remote.rates.error.StatusTypes
import com.rulhouse.openexchangeratesdemo.remote.rates.error.StatusTypes.Companion.mapToStatus
import com.rulhouse.openexchangeratesdemo.remote.rates.repository.RatesApiRepository
import com.rulhouse.openexchangeratesdemo.remote.rates.service.RatesApiService
import com.rulhouse.openexchangeratesdemo.remote.response.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException


class RatesApiImpl(
    private val apiService: RatesApiService
) : RatesApiRepository {

    override suspend fun getRates(
        appId: String?,
        base: String?
    ): Flow<BaseResult<List<CurrencyRate>, StatusTypes>> {
        return flow {
            try {
                val response = apiService.getRatesInformation(appId = appId, base = base)
                val body = response.body()
                if (!response.isSuccessful) {
                    val errorBody: RatesApiErrorBody? = ErrorBodyParser().parse(response)
                    if (errorBody == null) {
                        emit(BaseResult.Error(mapToStatus(response.code(), null)))
                        return@flow
                    }
                    val status = mapToStatus(response.code(), errorBody.message)
                    emit(BaseResult.Error(status))
                    return@flow
                }
                if (body != null) {
                    val currencyRates: MutableList<CurrencyRate> = mutableListOf()
                    body.rates.forEach { currencyRates.add(Rate(it.key, it.value)) }
                    emit(BaseResult.Success(currencyRates))
                    return@flow
                }
                emit(BaseResult.Error(StatusTypes.NullBody))
                return@flow
            } catch (e: UnknownHostException) {
                emit(BaseResult.Error(StatusTypes.NetworkError))
                e.printStackTrace()
            } catch (e: Exception) {
                emit(BaseResult.Error(StatusTypes.UnknownError))
                e.printStackTrace()
            }
        }
    }

}