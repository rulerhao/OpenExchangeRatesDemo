package com.rulhouse.openexchangeratesdemo.view.components.currency_button

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.CurrencyRate

@Composable
fun CurrencyButton(
    rate: CurrencyRate,
    onButtonEvent: (CurrencyButtonEvent) -> Unit
) {
    Button(
        onClick = { onButtonEvent(CurrencyButtonEvent.OnButtonClick(rate)) }
    ) {
        Text(text = rate.currency)
    }
}