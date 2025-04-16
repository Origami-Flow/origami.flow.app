package com.trancas.salgado.ui.components.stock

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.trancas.salgado.R
import com.trancas.salgado.screens.stock.StockViewModel
import com.trancas.salgado.screens.stock.classes.Product
import com.trancas.salgado.screens.stock.classes.Stock
import com.trancas.salgado.ui.components.shared.AlertDialog
import com.trancas.salgado.ui.theme.flame_pea
import kotlinx.coroutines.launch

@Composable
fun ProductCard(
    product: Product,
    stock: Stock,
    stockViewModel: StockViewModel
) {
    var quantity by remember { mutableIntStateOf(stock.quantidade) }
    var exibirDialogo by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(color = flame_pea)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            val imagePainter = if (product.imagem != null) {
                rememberAsyncImagePainter(product.imagem)
            } else {
                painterResource(id = R.drawable.product_default)
            }

            Image(
                painter = imagePainter,
                contentDescription = stringResource(id = R.string.image_description, product.nome),
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = product.nome,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            if (quantity > 0) {
                                stockViewModel.atualizarQuantidadeERecarregar(
                                    stockId = stock.id,
                                    quantidade = -1,
                                    onUpdated = { estoqueAtualizado ->
                                        quantity = estoqueAtualizado?.quantidade ?: quantity
                                    }
                                )
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.KeyboardArrowDown,
                                contentDescription = stringResource(id = R.string.icon_description, "Diminuir quantidade"),
                                tint = Color.White
                            )
                        }

                        Text(
                            text = quantity.toString(),
                            fontSize = 24.sp,
                            color = Color.White
                        )

                        IconButton(onClick = {
                            stockViewModel.atualizarQuantidadeERecarregar(
                                stockId = stock.id,
                                quantidade = 1,
                                onUpdated = { estoqueAtualizado ->
                                    quantity = estoqueAtualizado?.quantidade ?: quantity
                                }
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.KeyboardArrowUp,
                                contentDescription = stringResource(id = R.string.icon_description, "Aumentar quantidade"),
                                tint = Color.White
                            )
                        }
                    }

                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = stringResource(id = R.string.icon_description, "Editar produto"),
                            tint = Color.White
                        )
                    }

                    IconButton(onClick = { exibirDialogo = true}) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = stringResource(id = R.string.icon_description, "Excluir produto"),
                            tint = Color.White
                        )
                    }


                }
            }
        }
    }

    val context = LocalContext.current

    AlertDialog(
        exibirDialogo = exibirDialogo,
        onDismissRequest = { exibirDialogo = false },
        onConfirm = {
            exibirDialogo = false

            coroutineScope.launch {
                try {
                    stockViewModel.removerEstoque(stock)
                    stockViewModel.removerProduto(product)

                    Toast.makeText(context, "Produto excluído com sucesso", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(context, "Erro ao excluir produto", Toast.LENGTH_SHORT).show()
                    Log.e("ProductCard", "Erro ao excluir: ${e.message}")
                }
            }
        },
        onDismiss = {
            exibirDialogo = false
        },
        text = stringResource(id = R.string.alert_dialog_content, "O produto ${product.nome} será excluído")
    )

}

