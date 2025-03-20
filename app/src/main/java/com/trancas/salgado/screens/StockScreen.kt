package com.trancas.salgado.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trancas.salgado.R
import com.trancas.salgado.ui.components.shared.SearchBar
import com.trancas.salgado.ui.components.stock.ProductCard
import com.trancas.salgado.ui.theme.flame_pea

data class Product(
    val name: String,
    val quantity: Int,
    val imageUrl: String
)

@Composable
fun StockScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val products = listOf(
        Product(
            "Pomada",
            10,
            "https://br.freepik.com/vetores-gratis/fundo-geometrico-do-teste-padrao_1103731.htm#fromView=keyword&page=1&position=2&uuid=8e14def3-3ce4-4268-80e7-d31ff79060bd&query=Padrao+Geometrico"
        ),
        Product("Creme", 5, "https://example.com/creme.jpg"),
        Product("Sabonete", 20, "https://example.com/sabonete.jpg"),
        Product("Shampoo", 15, "https://example.com/shampoo.jpg"),
        Product("Condicionador", 8, "https://example.com/condicionador.jpg")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Text(
                text = "Estoque",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(3.dp)
            )
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(color = flame_pea, CircleShape)
                    .clickable {
                        navController.navigate("add_product_screen")
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Adicionar", tint = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            query = searchQuery,
            onQueryChanged = { searchQuery = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            val filteredProducts =
                products.filter { it.name.contains(searchQuery, ignoreCase = true) }

            items(filteredProducts) { product ->
                ProductCard(product)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }
}