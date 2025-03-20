package com.trancas.salgado.ui.components.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.trancas.salgado.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInputField(label: String, type: String, onTipoChange: ((String) -> Unit)? = null) {
    var text by remember { mutableStateOf("") }

    val keyboardOptions = when (label) {
        "Preço de venda (R$)", "Preço de compra (R$)" -> KeyboardOptions(keyboardType = KeyboardType.Decimal)
        "Quantidade por unidade", "Quantidade em estoque" -> KeyboardOptions(keyboardType = KeyboardType.Number)
        else -> KeyboardOptions.Default
    }

    if (type == "input") {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it },
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = keyboardOptions,
            supportingText = {
                if (text.isBlank()) {
                    Text("Campo obrigatório", color = Color.Red)
                }
            }
        )
    }

    if (type == "menu") {
        var expanded by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("") }

        val typeOptions = listOf("Salão", "Loja")
        val quantityOptions = listOf("ml", "mg", "gr", "kl")

        Box(modifier = Modifier.fillMaxWidth()) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedOption,
                    onValueChange = {
                        selectedOption = it
                        onTipoChange?.invoke(it)
                    },
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    label = { Text(label) },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_down),
                            contentDescription = "Abrir menu",
                            modifier = Modifier
                                .size(12.dp)
                                .clickable { expanded = !expanded }
                        )
                    },
                    supportingText = {
                        if (selectedOption.isBlank()) {
                            Text("Selecione uma opção", color = Color.Red)
                        }
                    }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    if (label == "Tipo") {
                        typeOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedOption = option
                                    expanded = false
                                    onTipoChange?.invoke(option)
                                },

                            )
                        }
                    } else {
                        quantityOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedOption = option
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}