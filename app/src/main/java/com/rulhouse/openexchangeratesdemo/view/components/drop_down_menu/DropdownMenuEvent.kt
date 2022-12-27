package com.rulhouse.openexchangeratesdemo.view.components.drop_down_menu

import com.rulhouse.openexchangeratesdemo.remote.rates.dto.CurrencyRate

sealed class DropdownMenuEvent {
    data class OnRateSelect(val rate: CurrencyRate): DropdownMenuEvent()
    object OnExpand: DropdownMenuEvent()
    object OnDismissRequest: DropdownMenuEvent()
}