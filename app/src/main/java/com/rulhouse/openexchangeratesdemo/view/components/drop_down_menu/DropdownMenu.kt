package com.rulhouse.openexchangeratesdemo.view.components.drop_down_menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.CurrencyRate

@Composable
fun DropDownMenu(
    item: CurrencyRate,
    expanded: Boolean,
    onEvent: (DropdownMenuEvent) -> Unit,
    list: List<CurrencyRate>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Button(
            onClick = { onEvent(DropdownMenuEvent.OnExpand) },
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterEnd),
        ) {
            Text(text = item.currency)
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
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
    }
}