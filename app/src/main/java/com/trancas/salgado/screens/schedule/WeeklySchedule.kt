package com.trancas.salgado.screens.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.trancas.salgado.R
import com.trancas.salgado.screens.schedule.classes.Event
import com.trancas.salgado.ui.components.schedule.DateCarousel
import com.trancas.salgado.ui.components.schedule.EventCard
import com.trancas.salgado.ui.components.schedule.ScheduleDrawer
import com.trancas.salgado.ui.components.shared.MonthDropdown
import com.trancas.salgado.ui.components.shared.YearDropdown
import com.trancas.salgado.ui.theme.flame_pea
import com.trancas.salgado.ui.theme.pale_pink
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun WeeklySchedule(navController: NavController) {
    val today = remember { LocalDate.now() }
    var selectedDate by remember { mutableStateOf(today) }
    var selectedMonth by remember { mutableIntStateOf(today.monthValue) }
    var selectedYear by remember { mutableIntStateOf(today.year) }

    val firstDayOfMonth = LocalDate.of(selectedYear, selectedMonth, 1)
    val daysInMonth = firstDayOfMonth.lengthOfMonth()
    val datesInMonth = (1..daysInMonth).map {
        LocalDate.of(selectedYear, selectedMonth, it)
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val viewModel: WeeklyScheduleViewModel = viewModel()
    val eventos by viewModel.eventos.collectAsState()
    ModalNavigationDrawer(
        drawerContent = {
            ScheduleDrawer(drawerState, scope) { viewType ->
                when (viewType) {
                    "month" -> navController.navigate("monthly_schedule")
                    "week" -> navController.navigate("weekly_schedule")
                    "day" -> navController.navigate("agenda")
                }
            }
        },
        drawerState = drawerState,
    ) {
        Scaffold { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 15.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint = Color.Black
                            )
                        }
                        Text(
                            text = stringResource(id = R.string.proximo_agendamento),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f).padding(10.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(color = flame_pea, shape = CircleShape)
                                .clickable { navController.navigate("createAgendamento") },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Adicionar", tint = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        MonthDropdown(
                            selectedMonth = selectedMonth,
                            onMonthSelected = { month -> selectedMonth = month }
                        )
                        YearDropdown(
                            selectedYear = selectedYear,
                            onYearSelected = { year ->
                                selectedYear = year
                                selectedDate = LocalDate.of(year, selectedMonth, 1)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    DateCarousel(
                        selectedDate = selectedDate,
                        onDateSelected = { selectedDate = it },
                        dates = datesInMonth
                    )
                    LaunchedEffect(selectedDate) {
                        viewModel.buscarEventoPorData(selectedDate.toString())
                    }

                    if (eventos.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .height(500.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Nenhum evento encontrado", fontWeight = FontWeight.Bold)
                        }
                    } else {
                        LazyColumn(modifier = Modifier.height(500.dp)) {
                            items(eventos.size) { index ->
                                EventCard(item = eventos[index], navController = navController)
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }

                }
        }
    }
}
