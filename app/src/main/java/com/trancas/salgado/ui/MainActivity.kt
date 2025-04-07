package com.trancas.salgado.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trancas.salgado.screens.ClientListScreen
import com.trancas.salgado.screens.stock.AddProductScreen
import com.trancas.salgado.screens.FinancesScreen
import com.trancas.salgado.screens.MainScreen
import com.trancas.salgado.screens.MetricsScreen
import com.trancas.salgado.screens.stock.ProductViewModel
import com.trancas.salgado.screens.stock.StockScreen
import com.trancas.salgado.screens.stock.StockViewModel
import com.trancas.salgado.ui.theme.AppTheme
import com.trancas.salgado.ui.components.shared.navbar.BottomNavBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                var selectedRoute by remember { mutableStateOf("agenda") }

                Scaffold(
                    bottomBar = {
                        BottomNavBar(selectedRoute = selectedRoute) { route ->
                            selectedRoute = route
                            navController.navigate(route){
                                popUpTo = navController.graph.startDestinationId
                                launchSingleTop = true
                            }
                        }
                    }
                )
                { paddingValues ->
                    Column(modifier = Modifier.padding(paddingValues)) {
                        NavigationGraph(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "agenda") {
        composable("agenda") { MainScreen() }
        composable("estoque") { StockScreen(navController, ProductViewModel(), StockViewModel()) }
        composable("add_product_screen") { AddProductScreen(navController) }
        composable("metricas") { MetricsScreen() }
        composable("clientes") { ClientListScreen() }
        composable("financas") { FinancesScreen() }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}