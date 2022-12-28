package com.rulhouse.openexchangeratesdemo.view.components.currency_button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.CurrencyRate

@Composable
fun CurrencyButton(
    rate: CurrencyRate,
    onButtonEvent: (CurrencyButtonEvent) -> Unit
) {
    Button(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize(),
        onClick = { onButtonEvent(CurrencyButtonEvent.OnButtonClick(rate)) }
    ) {
        Text(text = rate.currency)
    }
}