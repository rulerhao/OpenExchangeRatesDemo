package com.rulhouse.openexchangeratesdemo.remote.air_pollution.dto

data class RatesApiInformation(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: Rates,
    val timestamp: Double
)