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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trancas.salgado.screens.MainScreen
import androidx.navigation.navArgument
import com.trancas.salgado.screens.client.EditClientScreen
import com.trancas.salgado.screens.clients.ClientsScreen
import com.trancas.salgado.screens.event.CreateEventScreen
import com.trancas.salgado.screens.event.EditEventScreen
import com.trancas.salgado.screens.extract.ExtractScreen
import com.trancas.salgado.screens.finances.FinancesScreen
import com.trancas.salgado.screens.metrics.MetricsScreen
import com.trancas.salgado.screens.schedule.WeeklySchedule
import com.trancas.salgado.screens.stock.AddProductScreen
import com.trancas.salgado.screens.stock.StockScreen
import com.trancas.salgado.screens.stock.StockViewModel
import com.trancas.salgado.screens.stock.UpdateProductScreen
import com.trancas.salgado.ui.theme.AppTheme
import com.trancas.salgado.ui.components.shared.navbar.BottomNavBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                var selectedRoute by remember { mutableStateOf("weekly_schedule") }

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
    NavHost(navController = navController, startDestination = "weekly_schedule") {
        composable("agenda") { MainScreen(navController) }
        composable("estoque") { StockScreen(navController) }
        composable("add_product_screen") { AddProductScreen(navController) }
        composable("metricas") { MetricsScreen() }
        composable("clientes") { ClientsScreen(navController = navController) }
        composable("financas") { FinancesScreen(navController = navController) }
        composable("weekly_schedule") { WeeklySchedule(navController) }
        composable("createAgendamento") { CreateEventScreen(navController = navController) }
        composable("editAgendamento/{id}") { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (eventId != null) {
                EditEventScreen(eventId = eventId, navController = navController)
            } }
        composable("extractScreen") { ExtractScreen(navController = navController) }
        composable("clientsScreen") { ClientsScreen(navController = navController) }
        composable(route = "EditClientScreen/{clientId}") {backStackEntry ->
            val id = backStackEntry.arguments?.getString("clientId")?.toIntOrNull()
            if (id != null) {
                EditClientScreen(clientId = id, navController = navController)
            }
        }
        composable("updateProduct/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments!!.getInt("productId")
            UpdateProductScreen(navController, productId)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    locale = "pt-BR",)
@Composable
fun GreetingPreview() {
    MainScreen(navController = rememberNavController())
}