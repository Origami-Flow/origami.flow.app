package com.trancas.salgado.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trancas.salgado.ui.components.shared.AddButton
import com.trancas.salgado.ui.components.shared.SearchBar
import com.trancas.salgado.ui.components.shared.clients.ClientCard
import com.trancas.salgado.ui.components.stock.ProductCard

data class Client(
    val name: String,
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ClientsScreen() {
    var searchQuery by remember { mutableStateOf("") }
    val clients = listOf(
        Client("Larissa"),
        Client("Julia"),
        Client("Camila"),
        Client("Luiza"),
        Client("Sabrina"),
        Client("Maya")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Clientes", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            AddButton(onClick = { })
        }

        Spacer(modifier = Modifier.height(10.dp))

        SearchBar(
            query = searchQuery,
            onQueryChanged = { searchQuery = it }
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            val filteredProducts =
                clients.filter { it.name.contains(searchQuery, ignoreCase = true) }

            items(filteredProducts) { client ->
                ClientCard(client)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClientsScreenPreview() {
    ClientsScreen()
}