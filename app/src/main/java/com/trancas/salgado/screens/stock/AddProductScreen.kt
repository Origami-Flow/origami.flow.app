package com.trancas.salgado.screens.stock

import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.trancas.salgado.R
import com.trancas.salgado.screens.stock.classes.Product
import com.trancas.salgado.ui.components.shared.CustomButton
import com.trancas.salgado.ui.components.shared.CustomInputField
import com.trancas.salgado.ui.components.stock.ImagePicker
import com.trancas.salgado.ui.utils.prepareFilePart

@Composable
fun AddProductScreen(navController: NavController) {
    var showPrecoVenda by remember { mutableStateOf(false) }
    val addProductViewModel: AddProductViewModel = viewModel()
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    var nomeProduto by remember { mutableStateOf("") }
    var precoCompra by remember { mutableStateOf("") }
    var precoVenda by remember { mutableStateOf("") }
    var unidadeMedida by remember { mutableStateOf("") }
    var quantidadeEmbalagem by remember { mutableStateOf("") }
    var quantidadeEstoque by remember { mutableStateOf("") }
    var marcaProduto by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }

    val setNomeProduto: (String) -> Unit = { nomeProduto = it }
    val setPrecoCompra: (String) -> Unit = { precoCompra = it }
    val setUnidadeMedida: (String) -> Unit = { unidadeMedida = it }
    val setQuantidadeEmbalagem: (String) -> Unit = { quantidadeEmbalagem = it }
    val setQuantidadeEstoque: (String) -> Unit = { quantidadeEstoque = it }
    val setPrecoVenda: (String) -> Unit = { precoVenda = it }
    val setMarcaProduto: (String) -> Unit = { marcaProduto = it }

    val campos = listOf(
        Triple(stringResource(id = R.string.nome_do_produto), "input", setNomeProduto),
        Triple(stringResource(id = R.string.marca_do_produto), "input", setMarcaProduto),
        Triple(stringResource(id = R.string.preco_de_compra), "input", setPrecoCompra),
        Triple(stringResource(id = R.string.unidade_de_medida), "menu", setUnidadeMedida),
        Triple(stringResource(id = R.string.quantidade_por_unidade), "input", setQuantidadeEmbalagem),
        Triple(stringResource(id = R.string.quantidade_em_estoque), "input", setQuantidadeEstoque),
    ).toMutableList()

    if (showPrecoVenda) {
        campos.add(3, Triple(stringResource(id = R.string.preco_de_venda), "input", setPrecoVenda))
    }

    val saveResult = addProductViewModel.saveResult
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
                text = stringResource(id = R.string.stock_screen),
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
                contentDescription = stringResource(id = R.string.back_button),
                modifier = Modifier.size(20.dp)
                    .clickable {
                        navController.navigate("estoque")
                    },
            )
            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = stringResource(id = R.string.add_product_screen),
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
                CustomInputField("Tipo", "menu", value = tipo) { selectedTipo ->
                    tipo = selectedTipo
                    showPrecoVenda = selectedTipo == "Loja"
                }
            }

            items(campos) { (label, type, onValueChange) ->
                val value = when (label) {
                    stringResource(id = R.string.nome_do_produto) -> nomeProduto
                    stringResource(id = R.string.preco_de_compra) -> precoCompra
                    stringResource(id = R.string.unidade_de_medida) -> unidadeMedida
                    stringResource(id = R.string.quantidade_por_unidade) -> quantidadeEmbalagem
                    stringResource(id = R.string.quantidade_em_estoque) -> quantidadeEstoque
                    stringResource(id = R.string.marca_do_produto) -> marcaProduto
                    stringResource(id = R.string.preco_de_venda) -> precoVenda
                    else -> ""
                }

                CustomInputField(label, type, value, onValueChange)
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
                    ImagePicker(selectedImageUri) { uri ->
                        selectedImageUri = uri
                    }

                    val filePart = selectedImageUri?.let { prepareFilePart(context, it) }

                    CustomButton(stringResource(id = R.string.save_button), onClick = {
                        if (filePart == null) {
                            Toast.makeText(context, "Por favor, selecione uma imagem.", Toast.LENGTH_SHORT).show()
                        }
                        Log.d("API", "Imagem selecionada: $filePart")

                        val novoProduto = Product(
                            nome = nomeProduto,
                            marca = marcaProduto,
                            valorCompra = precoCompra.toDoubleOrNull() ?: 0.0,
                            valorVenda = if (showPrecoVenda) precoVenda.toDoubleOrNull() ?: 1.0 else 1.0,
                            quantidadeEmbalagem = quantidadeEmbalagem.toIntOrNull() ?: 0,
                            unidadeMedida = unidadeMedida,
                            tipo = if (tipo == "Salão") "SALAO" else "LOJA",
                            imagem = filePart,
                            quantidade = quantidadeEstoque.toIntOrNull() ?: 0,
                            salaoId = 1
                        )

                        addProductViewModel.salvarProduto(novoProduto)
                    })
                    LaunchedEffect(saveResult) {
                        saveResult?.let {
                            if (it.isSuccess) {
                                Toast.makeText(context, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show()
                                navController.navigate("estoque")
                            } else {
                                Log.e("API", "Erro ao salvar produto: ${it.exceptionOrNull()}")
                                Toast.makeText(context, "Erro ao salvar produto", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddProductScreenPreview() {
    AddProductScreen(navController = NavController(LocalContext.current))
}