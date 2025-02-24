package com.trancas.salgado.ui.theme.components.shared.navbar

import com.trancas.salgado.R

sealed class BottomNavItem(
    val title: String,
    val route: String,
    val icon: Int
) {
    data object Finance : BottomNavItem(
        route = "finance",
        icon = R.drawable.close,
        title = "Finanças",
    )
    data object Storage : BottomNavItem(
        route = "storage",
        icon = R.drawable.close,
        title = "Estoque",
    )
    data object Schedule : BottomNavItem(
        route = "schedule",
        icon = R.drawable.close,
        title = "Agenda",
    )
    data object Clients : BottomNavItem(
        route = "clients",
        icon = R.drawable.close,
        title = "Clientes",
    )
    data object Kpis : BottomNavItem(
        route = "kpis",
        icon = R.drawable.close,
        title = "Métricas",
    )
}
