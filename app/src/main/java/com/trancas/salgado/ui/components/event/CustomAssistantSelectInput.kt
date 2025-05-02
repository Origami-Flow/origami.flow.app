package com.trancas.salgado.ui.components.event

import com.trancas.salgado.screens.event.AssistantData

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAssistantSelectInput(
    listOptions: List<AssistantData>,
    selectedAssistant: String,
    label: String,
    onOptionSelected: ((Int?, String) -> Unit)? = null
) {
    var showDialog by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    var requestDialog by remember { mutableStateOf(false) }

    LaunchedEffect(requestDialog) {
        if (requestDialog) {
            focusManager.clearFocus(force = true)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedAssistant,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth()
                .onFocusEvent {
                    if (it.isFocused) {
                        showDialog = true
                        requestDialog = true
                    }
                }
        )
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Concluir")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    onOptionSelected?.invoke(null, "")
                }) {
                    Text("Limpar")
                }
            },
            title = { Text("Selecione a Auxiliar") },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .heightIn(max = 300.dp)
                        .fillMaxWidth()
                ) {
                    items(count = listOptions.size) { index ->
                        val option = listOptions[index]
                        Text(
                            text = "${option.name} - ${option.email}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                                .clickable {
                                    onOptionSelected?.invoke(option.id, option.name)
                                    showDialog = false
                                }
                        )
                    }
                }
            }
        )
    }
}