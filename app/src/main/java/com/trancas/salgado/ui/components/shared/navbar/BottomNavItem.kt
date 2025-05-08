package com.trancas.salgado.ui.components.shared.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("Finanças", "financas", Icons.Outlined.List),
    BottomNavItem("Estoque", "estoque", Icons.Outlined.ShoppingCart),
    BottomNavItem("Agenda", "weekly_schedule", Icons.Outlined.DateRange),
    BottomNavItem("Clientes", "clientes", Icons.Outlined.Person),
    BottomNavItem("Métricas", "metricas", Icons.Outlined.Search)
)
