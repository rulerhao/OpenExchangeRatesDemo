package com.rulhouse.openexchangeratesdemo.view.components.input_textfield

data class InputTextFieldState(
    val value: String = "",
    val isNumeric: Boolean = false
) {
    fun isNumeric(text: String): Boolean {
        return try {
            text.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}