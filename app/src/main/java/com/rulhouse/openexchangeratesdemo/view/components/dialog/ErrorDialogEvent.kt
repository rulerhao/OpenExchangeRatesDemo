package com.rulhouse.openexchangeratesdemo.view.components.dialog

sealed class ErrorDialogEvent {
    object OnDismiss: ErrorDialogEvent()
}