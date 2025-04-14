package com.trancas.salgado.screens.stock

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trancas.salgado.ui.components.shared.SearchBar
import com.trancas.salgado.ui.components.stock.ProductCard
import com.trancas.salgado.ui.theme.flame_pea
import com.trancas.salgado.ui.theme.pale_pink
import kotlinx.coroutines.delay
import java.util.StringTokenizer
import kotlin.math.min
import com.trancas.salgado.R

@Composable
fun StockScreen(navController: NavController, stockViewModel: StockViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    var debouncedQuery by remember { mutableStateOf("") }

    val estoques by stockViewModel.listaEstoques.collectAsState()
    val produtos by stockViewModel.listaProdutos.collectAsState()
    val erros = stockViewModel.erros

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
                text = stringResource(id = R.string.stock_screen),
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
                Icon(Icons.Filled.Add, contentDescription = stringResource(id = R.string.add_product_screen), tint = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LaunchedEffect(searchQuery) {
            delay(500)
            debouncedQuery = searchQuery
            stockViewModel.buscarPorNome(debouncedQuery.trim())
        }

        SearchBar(
            query = searchQuery,
            onQueryChanged = { newQuery ->
                searchQuery = newQuery
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (erros.isNotEmpty()) {
            val txtErros = erros.map { "❌ $it" }.joinToString("\n")
            Text(
                txtErros,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(pale_pink)
                    .fillMaxWidth()
            )

            LaunchedEffect(txtErros) {
                val timeToCleanErrors = min(
                    (StringTokenizer(txtErros).countTokens().toLong()) * 1000,
                    5000L
                )
                delay(timeToCleanErrors)

                stockViewModel.limparErros()
            }
        }

        if (stockViewModel.isChamandoApi()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            val estoquesMap = remember(estoques) {
                estoques.associateBy { it.id }
            }

            LazyColumn {
                items(produtos, key = { it.id!! }) { product ->
                    val estoque = estoquesMap[product.id]

                    estoque?.let {
                        ProductCard(product = product, stock = estoque, stockViewModel = stockViewModel)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

    }
}