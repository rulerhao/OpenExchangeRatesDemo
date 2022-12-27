package com.rulhouse.openexchangeratesdemo.view.components.currency_button

import com.rulhouse.openexchangeratesdemo.remote.rates.dto.CurrencyRate

sealed class CurrencyButtonEvent {
    data class OnButtonClick(val rate: CurrencyRate): CurrencyButtonEvent()
}