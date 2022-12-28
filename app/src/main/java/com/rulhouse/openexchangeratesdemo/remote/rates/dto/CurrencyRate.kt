package com.rulhouse.openexchangeratesdemo.remote.rates.dto

interface CurrencyRate {
    val currency: String
    val value: Double
}