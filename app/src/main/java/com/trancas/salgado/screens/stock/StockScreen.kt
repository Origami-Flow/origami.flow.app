package com.trancas.salgado.screens.stock

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trancas.salgado.screens.stock.classes.Product
import com.trancas.salgado.ui.components.shared.SearchBar
import com.trancas.salgado.ui.components.stock.ProductCard
import com.trancas.salgado.ui.theme.flame_pea
import com.trancas.salgado.ui.theme.pale_pink
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.StringTokenizer
import kotlin.math.min

@Composable
fun StockScreen(navController: NavController, productViewModel: ProductViewModel, stockViewModel: StockViewModel) {
    var searchQuery by remember { mutableStateOf("") }

    val produtos = productViewModel.lista
    val estoques = stockViewModel.lista
    val errosProdutos = productViewModel.erros
    val coroutineScope = rememberCoroutineScope()

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

        if (errosProdutos.isNotEmpty()) {
            val txtErros = errosProdutos.map { "❌ $it" }.joinToString("\n")
            Text(
                txtErros,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(pale_pink)
                    .fillMaxWidth()
            )

            coroutineScope.launch {
                delay(
                    min(
                        (StringTokenizer(txtErros).countTokens().toLong())
                                * 1000,
                        5000
                    )
                )
                productViewModel.limparErros()
            }
        }

        if (productViewModel.isChamandoApi()) {
            Text("Carregando... ")
        } else {
            LazyColumn {
                items(produtos, key = { it.id }) { product ->
                    val estoque = stockViewModel.estoquePorId(product.id)

                    estoque?.let {
                        ProductCard(product = product, stock = estoque)
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }
            }
        }

    }
}