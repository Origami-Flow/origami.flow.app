package com.trancas.salgado.ui.theme.components.shared.navbar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trancas.salgado.ui.theme.mos_green
import com.trancas.salgado.ui.theme.pale_pink

@Composable
fun BottomNavBar(
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    val bottomNavItems = listOf(
        BottomNavItem.Finance,
        BottomNavItem.Storage,
        BottomNavItem.Schedule,
        BottomNavItem.Clients,
        BottomNavItem.Kpis
    )

    NavigationBar(containerColor = mos_green) {
        bottomNavItems.forEach { item ->
            val isSelected = selectedRoute == item.route
            NavigationBarItem(
                selected = isSelected,
                onClick = { if (!isSelected) onItemSelected(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = if (isSelected) pale_pink else pale_pink,
                        modifier = Modifier.size(40.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 14.sp,
                        color = pale_pink
                    )
                },
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}
