package com.rulhouse.openexchangeratesdemo.view.components.currency_panel

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.CurrencyRate
import com.rulhouse.openexchangeratesdemo.view.components.currency_button.CurrencyButton
import com.rulhouse.openexchangeratesdemo.view.components.currency_button.CurrencyButtonEvent

@Composable
fun CurrencyPanel(
    items: List<CurrencyRate>,
    onButtonEvent: (CurrencyButtonEvent) -> Unit
) {

    LazyVerticalGrid(
        modifier = Modifier.padding(8.dp),
        columns = GridCells.Adaptive(minSize = 120.dp)
    ) {
        items(items) { item ->
            CurrencyButton(
                rate = item,
                onButtonEvent = { onButtonEvent(it) }
            )
        }
    }

}