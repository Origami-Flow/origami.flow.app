package com.trancas.salgado.screens.clients

import android.annotation.SuppressLint
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.trancas.salgado.R
import com.trancas.salgado.ui.components.shared.AddButton
import com.trancas.salgado.ui.components.shared.SearchBar
import com.trancas.salgado.ui.components.clients.ClientCard
import com.trancas.salgado.ui.theme.flame_pea

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ClientsScreen(viewModel: ClientViewModel = viewModel(), navController: NavController) {

    val searchQuery by viewModel.searchQuery.collectAsState()

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
            Text(text = stringResource(id = R.string.text_clientes), fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(color = flame_pea, CircleShape)
                    .clickable {
                        navController.navigate("createCliente")
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Adicionar", tint = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        SearchBar(
            query = searchQuery,
            onQueryChanged = { viewModel.updateSearchQuery(it) }
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            val filteredClients = viewModel.getFilteredClients()

            items(filteredClients) { client ->
                ClientCard(client, navController = navController)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true, locale = "pt")
@Composable
fun ClientsScreenPreview() {
//    ClientsScreen()
}