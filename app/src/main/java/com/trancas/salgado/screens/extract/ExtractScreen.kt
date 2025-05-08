package com.trancas.salgado.screens.extract

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material3.*
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
import com.trancas.salgado.screens.extract.classes.ExpenseData
import com.trancas.salgado.screens.extract.classes.TreatmentData
import com.trancas.salgado.ui.theme.ColorNegative
import com.trancas.salgado.ui.theme.ColorPositive
import com.trancas.salgado.ui.theme.DividerColor
import com.trancas.salgado.ui.theme.GreenBorder
import com.trancas.salgado.ui.theme.pale_pink
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun ExtractScreen(viewModel: ExtractViewModel = viewModel(), navController: NavController) {
    val transacoes by viewModel.transactions.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.extrato),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { navController.navigate("financas") },
            border = BorderStroke(1.dp, GreenBorder),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 6.dp),
            modifier = Modifier.height(30.dp)
        ) {
            Text(
                text = stringResource(id = R.string.botao_voltar),
                color = GreenBorder,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            val allTransactions = transacoes.sortedByDescending { transaction ->
                when (transaction) {
                    is ExpenseData -> transaction.date
                    is TreatmentData -> LocalDateTime.parse(transaction.event.dataHoraInicio).toLocalDate().toString()
                    else -> ""
                }
            }

            allTransactions.groupBy { transaction ->
                when (transaction) {
                    is ExpenseData -> transaction.date
                    is TreatmentData -> LocalDateTime.parse(transaction.event.dataHoraInicio).toLocalDate().toString()
                    else -> ""
                }
            }.forEach { (sectionTitle, transactions) ->
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    ) {
                        Text(
                            text = sectionTitle,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(pale_pink, shape = RoundedCornerShape(50))
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    HorizontalDivider(thickness = 1.dp, color = DividerColor)
                }

                items(transactions) { transaction ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = when (transaction) {
                                is TreatmentData -> Icons.Default.ArrowCircleDown
                                is ExpenseData -> Icons.Default.ArrowCircleUp
                                else -> Icons.Default.ArrowCircleUp
                            },
                            contentDescription = null,
                            tint = when (transaction) {
                                is TreatmentData -> ColorPositive
                                is ExpenseData -> ColorNegative
                                else -> Color.Gray
                            },
                            modifier = Modifier.size(32.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                when (transaction) {
                                    is ExpenseData -> transaction.date
                                    is TreatmentData -> LocalDateTime.parse(transaction.event.dataHoraInicio).toLocalDate().toString()
                                    else -> ""
                                },
                                color = Color.Gray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = when (transaction) {
                                    is TreatmentData -> transaction.client.name
                                    is ExpenseData -> transaction.name
                                    else -> ""
                                },
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Text(
                            text = "R$%.2f".format(
                                when (transaction) {
                                    is ExpenseData -> transaction.value
                                    is TreatmentData -> transaction.value
                                    else -> 0.0
                                }
                            ),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
                item {
                    val lucroDia = viewModel.calculateDailyProfit(transactions)

                    Row {
                        Text(
                            text = stringResource(
                                id = R.string.lucro_do_dia,
                                transactions.firstOrNull()?.let {
                                    when (it) {
                                        is ExpenseData -> it.date
                                        is TreatmentData -> LocalDateTime.parse(it.event.dataHoraInicio).toLocalDate().toString()
                                        else -> ""
                                    }
                                } ?: ""
                            ),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color.Black
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = "R$ %.2f".format(lucroDia),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, locale = "pt")
@Composable
fun ExtractScreenPreview() {
//    ExtractScreen()
}