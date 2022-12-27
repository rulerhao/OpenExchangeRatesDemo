package com.rulhouse.openexchangeratesdemo.remote.rates.dto

data class RatesApiInformation(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: Map<String, Double>,
    val timestamp: Double
)