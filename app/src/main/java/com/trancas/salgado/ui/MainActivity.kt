package com.trancas.salgado.ui

import TelaInicial
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
import com.example.teste.Login
import com.trancas.salgado.screens.AddProductScreen
import com.trancas.salgado.screens.MainScreen
import com.trancas.salgado.screens.StockScreen
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
        composable("estoque") { StockScreen(navController) }
        composable("add_product_screen") { AddProductScreen(navController) }
    }
}

class Navegacao : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "TelaInicial") {
                composable("TelaInicial") { TelaInicial(navController) }
                composable("Login") { Login(navController) }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}