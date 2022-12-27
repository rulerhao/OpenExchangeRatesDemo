package com.rulhouse.openexchangeratesdemo.rates_api

import com.rulhouse.openexchangeratesdemo.remote.rates.error.StatusTypes

class RatesApiMockItems {

    val correctUSDRateExpected = 1.0
    val invalidBaseStatusTestExpected = StatusTypes.NotAllowed
    val invalidAppIdStatusTestExpected = StatusTypes.InvalidAppId
    val missingAppIdStatusTestExpected = StatusTypes.MissingAppId
}