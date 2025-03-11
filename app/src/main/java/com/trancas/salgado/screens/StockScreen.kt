package com.trancas.salgado.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trancas.salgado.R
import com.trancas.salgado.ui.components.shared.SearchBar
import com.trancas.salgado.ui.components.stock.ProductCard

data class Product(
    val name: String,
    val quantity: Int,
    val imageUrl: String
)

@Composable
fun StockScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val products = listOf(
        Product("Pomada", 10, "https://br.freepik.com/vetores-gratis/fundo-geometrico-do-teste-padrao_1103731.htm#fromView=keyword&page=1&position=2&uuid=8e14def3-3ce4-4268-80e7-d31ff79060bd&query=Padrao+Geometrico"),
        Product("Creme", 5, "https://example.com/creme.jpg"),
        Product("Sabonete", 20, "https://example.com/sabonete.jpg"),
        Product("Shampoo", 15, "https://example.com/shampoo.jpg"),
        Product("Condicionador", 8, "https://example.com/condicionador.jpg")
    )

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
            Image(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "Adicionar",
                modifier = Modifier.size(40.dp)
                    .clickable {
                        navController.navigate("add_product_screen")
                    },
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            query = searchQuery,
            onQueryChanged = { searchQuery = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            val filteredProducts = products.filter { it.name.contains(searchQuery, ignoreCase = true) }

            items(filteredProducts) { product ->
                ProductCard(product)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }
}