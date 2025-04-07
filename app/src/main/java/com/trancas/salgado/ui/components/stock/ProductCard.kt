package com.trancas.salgado.ui.components.stock

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter
import com.trancas.salgado.R
import com.trancas.salgado.screens.stock.ProductApi
import com.trancas.salgado.screens.stock.ProductApiService
import com.trancas.salgado.screens.stock.ProductViewModel
import com.trancas.salgado.screens.stock.StockViewModel
import com.trancas.salgado.screens.stock.classes.Product
import com.trancas.salgado.screens.stock.classes.Stock
import com.trancas.salgado.ui.components.shared.AlertDialog

@Composable
fun ProductCard(
    product: Product,
    stock: Stock,
    productViewModel: ProductViewModel = ProductViewModel(),
    stockViewModel: StockViewModel = StockViewModel()) {
    var quantity by remember { mutableIntStateOf(stock.quantidade) }
    var exibirDialogo by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFB55B49))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            val imagePainter = painterResource(id = R.drawable.product_default)

            Image(
                painter = imagePainter,
                contentDescription = "Imagem do Produto",
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
                        IconButton(onClick = { if (quantity > 0) quantity-- }) {
                            Icon(
                                imageVector = Icons.Outlined.KeyboardArrowDown,
                                contentDescription = "Diminuir quantidade",
                                tint = Color.White
                            )
                        }

                        Text(
                            text = quantity.toString(),
                            fontSize = 24.sp,
                            color = Color.White
                        )

                        IconButton(onClick = { quantity++ }) {
                            Icon(
                                imageVector = Icons.Outlined.KeyboardArrowUp,
                                contentDescription = "Aumentar quantidade",
                                tint = Color.White
                            )
                        }
                    }

                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Editar produto",
                            tint = Color.White
                        )
                    }

                    IconButton(onClick = { exibirDialogo = true}) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Excluir produto",
                            tint = Color.White
                        )
                    }


                }
            }
        }
    }

    AlertDialog(
        exibirDialogo = exibirDialogo,
        onDismissRequest = { exibirDialogo = false },
        onConfirm = {
            stockViewModel.removerEstoque(stock)
            productViewModel.removerItem(product)
            exibirDialogo = false
        },
        onDismiss = {
            exibirDialogo = false
        },
        text = "O produto ${product.nome} será excluído. Deseja continuar?"
    )
}

