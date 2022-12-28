package com.rulhouse.openexchangeratesdemo.view.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorDialog(
    errorDialogState: ErrorDialogState,
    onDismiss: (ErrorDialogEvent) -> Unit
) {
    if (errorDialogState.isShow) {
        AlertDialog(
            onDismissRequest = { onDismiss(ErrorDialogEvent.OnDismiss) },
            title = { Text(text = "Error") },
            text = { Text(text = errorDialogState.content) },
            confirmButton = {
                Button(
                    onClick = {
                        onDismiss(ErrorDialogEvent.OnDismiss)
                    }
                ) {
                    Text(text = "Got it!")
                }
            }
        )
    }
}