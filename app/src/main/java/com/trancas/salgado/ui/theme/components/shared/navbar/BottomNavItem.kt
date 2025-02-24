package com.trancas.salgado.ui.theme.components.shared.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("Home", "home", Icons.Filled.Home),
    BottomNavItem("Clients", "clients", Icons.Filled.Person),
    BottomNavItem("Storage", "storage", Icons.Filled.ShoppingCart)
)
