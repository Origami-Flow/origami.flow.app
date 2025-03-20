package com.trancas.salgado.ui.components.stock

import androidx.compose.foundation.Image
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import coil.compose.rememberImagePainter
import com.trancas.salgado.screens.Product
import com.trancas.salgado.R

@Composable
fun ProductCard(product: Product) {
    var quantity by remember { mutableIntStateOf(product.quantity) }

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
            val imagePainter = rememberImagePainter(
                product.imageUrl,
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.product_default)
                }
            )

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
                    text = product.name,
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
                }
            }
        }
    }
}

