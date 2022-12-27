package com.rulhouse.openexchangeratesdemo.view.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.CurrencyRate

@Composable
fun DropDownMenu(
    item: CurrencyRate,
    expanded: Boolean,
    onExpand: () -> Unit,
    onDismissRequest: () -> Unit,
    onItemClick: (CurrencyRate) -> Unit,
    list: List<CurrencyRate>
) {
    Button(
        onClick = {
            onExpand()
        },
        Modifier.wrapContentSize()
    ) {
        Text(text = item.currency)
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest() }
    ) {
        list.forEach {
            DropdownMenuItem(
                text = { Text(text = it.value.toString()) },
                onClick = { onItemClick(it) },
                leadingIcon = { Text(text = it.currency) }
            )
        }
    }
}