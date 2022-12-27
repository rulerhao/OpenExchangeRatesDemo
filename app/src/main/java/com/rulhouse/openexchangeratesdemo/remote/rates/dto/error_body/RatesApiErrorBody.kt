package com.rulhouse.openexchangeratesdemo.remote.rates.dto.error_body

data class RatesApiErrorBody(
    val error: Boolean,
    val status: Int,
    val message: String,
    val description: String
)