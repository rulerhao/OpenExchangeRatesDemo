package com.rulhouse.openexchangeratesdemo.rates_api

import com.rulhouse.openexchangeratesdemo.remote.response.BaseResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RatesApiTest {

    val arrange = RatesApiTestArrange()

    private val mockItems = RatesApiMockItems() // Need to modify manually.

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun correctTest() = runTest {
        arrange.ratesApiUseCases.getRates(arrange.correctTest.first, arrange.correctTest.second).collectLatest { baseResult ->
            assertEquals(mockItems.correctUSDRateExpected, (baseResult as BaseResult.Success).data.find {it.currency == "USD"}!!.value, 0.0)
            assertEquals(mockItems.correctTWDRateExpected, baseResult.data.find {it.currency == "TWD"}!!.value, 0.0)
            assertEquals(mockItems.correctJPYRateExpected, baseResult.data.find {it.currency == "JPY"}!!.value, 0.0)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun invalidBaseTest() = runTest {
        arrange.ratesApiUseCases.getRates(arrange.invalidBaseTest.first, arrange.invalidBaseTest.second).collectLatest { baseResult ->
            assertEquals(mockItems.invalidBaseStatusTestExpected, (baseResult as BaseResult.Error).rawResponse)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun invalidAppIdTest() = runTest {
        arrange.ratesApiUseCases.getRates(arrange.invalidAppIdTest.first, arrange.invalidAppIdTest.second).collectLatest { baseResult ->
            assertEquals(mockItems.invalidAppIdStatusTestExpected, (baseResult as BaseResult.Error).rawResponse)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun missingAppIdTest() = runTest {
        arrange.ratesApiUseCases.getRates(arrange.missingAppIdTest.first, arrange.missingAppIdTest.second).collectLatest { baseResult ->
            assertEquals(mockItems.missingAppIdStatusTestExpected, (baseResult as BaseResult.Error).rawResponse)
        }
    }
}