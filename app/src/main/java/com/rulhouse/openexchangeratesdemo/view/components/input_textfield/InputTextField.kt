package com.rulhouse.openexchangeratesdemo.view.components.input_textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextField(
    value: String,
    onInputTextFieldEvent: (InputTextFieldEvent) -> Unit
) {

    val focusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = value,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
        placeholder = {
            PlaceHolder()
        },
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

@Composable
private fun PlaceHolder() {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "Please input",
        textAlign = TextAlign.End
    )
}