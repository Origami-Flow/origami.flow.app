package com.trancas.salgado.ui.components.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trancas.salgado.R
import kotlinx.coroutines.launch

@Composable
fun ScheduleDrawer(
    drawerState: DrawerState,
    scope: CoroutineScope,
    onMenuItemClick: (String) -> Unit
) {
    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                stringResource(id = R.string.agenda),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(30.dp))

            NavigationDrawerItem(
                icon = { Icon(Icons.Filled.CalendarMonth, contentDescription = "Month") },
                label = { Text(stringResource(id = R.string.menu_mensal)) },
                selected = false,
                onClick = {
                    scope.launch { drawerState.close() }
                    onMenuItemClick("month")
                }
            )

            NavigationDrawerItem(
                icon = { Icon(Icons.Filled.ViewWeek, contentDescription = "Week") },
                label = { Text(stringResource(id = R.string.menu_semanal)) },
                selected = false,
                onClick = {
                    scope.launch { drawerState.close() }
                    onMenuItemClick("week")
                }
            )

            NavigationDrawerItem(
                icon = { Icon(Icons.Filled.CalendarToday, contentDescription = "Day") },
                label = { Text(stringResource(id = R.string.menu_diaria)) },
                selected = false,
                onClick = {
                    scope.launch { drawerState.close() }
                    onMenuItemClick("day")
                }
            )
        }
    }
}
