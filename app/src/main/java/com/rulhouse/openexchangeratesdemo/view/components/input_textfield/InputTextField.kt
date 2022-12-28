package com.rulhouse.openexchangeratesdemo.view.components.input_textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextField(
    value: String,
    onInputTextFieldEvent: (InputTextFieldEvent) -> Unit
) {

    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
        placeholder = { Text(text = "Please input!") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        onValueChange = {
            onInputTextFieldEvent(InputTextFieldEvent.OnValueChange(it))
        },
        singleLine = true
    )
}