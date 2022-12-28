package com.rulhouse.openexchangeratesdemo.view.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.CurrencyRate
import com.rulhouse.openexchangeratesdemo.view.components.drop_down_menu.DropdownMenuEvent

@Composable
fun DropDownMenu(
    item: CurrencyRate,
    expanded: Boolean,
    onEvent: (DropdownMenuEvent) -> Unit,
    list: List<CurrencyRate>
) {
    Button(
        onClick = { onEvent(DropdownMenuEvent.OnExpand) },
        Modifier.wrapContentSize()
    ) {
        Text(text = item.currency)
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onEvent(DropdownMenuEvent.OnDismissRequest) },
    ) {
        list.forEach {
            DropdownMenuItem(
                text = { Text(text = (it.value / item.value).toString()) },
                onClick = { onEvent(DropdownMenuEvent.OnRateSelect(it)) },
                leadingIcon = { Text(text = it.currency) }
            )
        }
    }
}