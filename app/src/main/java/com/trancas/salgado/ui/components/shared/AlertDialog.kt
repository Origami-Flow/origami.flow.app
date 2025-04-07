package com.trancas.salgado.ui.components.shared

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.trancas.salgado.ui.theme.mos_green
import com.trancas.salgado.ui.theme.pale_pink

@Composable
fun AlertDialog(
    exibirDialogo: Boolean,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    text: String
) {
    if (exibirDialogo) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { onDismissRequest() },
            title = { Text("Confirmação") },
            text = { Text(text) },
            confirmButton = {
                TextButton(onClick = {
                    onConfirm()
                })
                {
                    Text("Sim", color = mos_green)
                }
            },
            dismissButton = {
                Button(onClick = {
                    onDismiss()
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = mos_green,
                        contentColor = pale_pink
                    )
                )
                {
                    Text("Não")

                }
            }
        )
    }
}