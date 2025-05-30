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
fun CustomInputField(
    label: String,
    type: String,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    onTipoChange: ((String) -> Unit)? = null
) {

    val keyboardOptions = when (label) {
        "Preço de venda (R$)", "Preço de compra (R$)", "Purchase price (R$)", "Sale price (R$)" -> KeyboardOptions(keyboardType = KeyboardType.Decimal)
        "Quantidade por unidade", "Quantidade em estoque", "Quantity per unit", "Quantity in stock" -> KeyboardOptions(keyboardType = KeyboardType.Number)
        else -> KeyboardOptions.Default
    }

    if (type == "input") {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                            },
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = keyboardOptions,
            supportingText = {
                if (value.isBlank()) {
                    Text("Campo obrigatório", color = Color.Red)
                }
            }
        )
    }

    if (type == "menu") {
        var expanded by remember { mutableStateOf(false) }

        val typeOptions = listOf("Salão", "Loja")
        val quantityOptions = listOf("ml", "mg", "gr", "kl")

        Box(modifier = Modifier.fillMaxWidth()) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = value,
                    onValueChange = {},
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
                        if (value.isBlank()) {
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
                                    onValueChange(option)
                                    onTipoChange?.invoke(option)
                                    expanded = false
                                },

                            )
                        }
                    } else {
                        quantityOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    onValueChange(option)
                                    onTipoChange?.invoke(option)
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