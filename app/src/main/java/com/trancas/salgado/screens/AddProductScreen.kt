package com.trancas.salgado.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trancas.salgado.R
import com.trancas.salgado.ui.components.shared.CustomButton
import com.trancas.salgado.ui.components.shared.CustomInputField

@Composable
fun AddProductScreen(navController: NavController) {
    var tipo by remember { mutableStateOf("") }
    var showPrecoVenda by remember { mutableStateOf(false) }

    val campos = listOf(
        "Nome do Produto" to "input",
        "Preço de compra (R$)" to "input",
        "Unidade de medida" to "menu",
        "Quantidade por unidade" to "input",
        "Quantidade em estoque" to "input"
    ).toMutableList()

    if (showPrecoVenda) {
        campos.add(2, "Preço de venda (R$)" to "input")
    }

    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Text(
                text = "Estoque",
                fontSize = 30.sp,
                fontWeight = FontWeight(600)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Voltar",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        navController.navigate("estoque")
                    },
            )
            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "Adicionar",
                fontSize = 25.sp
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                CustomInputField("Tipo", "menu") { selectedTipo ->
                    tipo = selectedTipo
                    showPrecoVenda = selectedTipo == "Loja"
                }
            }

            items(campos) { (label, type) ->
                CustomInputField(label, type)
            }

            item {
                Spacer(modifier = Modifier.height(6.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.add_image),
                        modifier = Modifier.size(45.dp),
                        contentDescription = "Adicionar imagem"
                    )

                    CustomButton("Salvar", onClick = {})
                }
            }
        }
    }
}