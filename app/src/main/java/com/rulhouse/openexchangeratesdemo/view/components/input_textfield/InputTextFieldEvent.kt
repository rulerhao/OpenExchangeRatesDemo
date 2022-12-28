package com.rulhouse.openexchangeratesdemo.view.components.input_textfield

sealed class InputTextFieldEvent {
    data class OnValueChange(val text: String): InputTextFieldEvent()
}
