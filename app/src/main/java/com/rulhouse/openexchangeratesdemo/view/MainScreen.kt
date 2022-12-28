package com.rulhouse.openexchangeratesdemo.view

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rulhouse.openexchangeratesdemo.view.components.drop_down_menu.DropDownMenu
import com.rulhouse.openexchangeratesdemo.view.components.currency_panel.CurrencyPanel
import com.rulhouse.openexchangeratesdemo.view.components.dialog.ErrorDialog
import com.rulhouse.openexchangeratesdemo.view.components.input_textfield.InputTextField

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current

    ErrorDialog(
        errorDialogState = viewModel.errorDialogState.value,
        onDismiss = { viewModel.onErrorDialogEvent(it) }
    )

    Column(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures ( onTap = { focusManager.clearFocus() } )
            }
            .padding(16.dp),
    ) {
        InputTextField(
            value = viewModel.inputTextFieldState.value.value,
            onInputTextFieldEvent = { viewModel.onInputTextFieldEvent(it) }
        )
        DropDownMenu(
            item = viewModel.rate.value,
            expanded = viewModel.dropdownMenuState.value.expand,
            onEvent = { viewModel.onDropdownMenuEvent(it) },
            list = viewModel.rates.value
        )
        CurrencyPanel(
            items = viewModel.rates.value,
            onButtonEvent = { viewModel.onCurrencyButtonEvent(it) }
        )

    }

}