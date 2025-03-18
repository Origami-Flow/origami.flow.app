package com.trancas.salgado.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trancas.salgado.ui.theme.components.shared.DatePicker
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun MainScreen() {
    fun Long.toBrazilianDateFormat(
        pattern: String = "dd 'de' MMMM 'de' yyyy"
    ): String {
        val date = Date(this)
        val formatter = SimpleDateFormat(
            pattern, Locale("pt", "BR")
        ).apply {
            timeZone = TimeZone.getTimeZone("GMT-3")
        }
        return formatter.format(date)
    }
    val hours = (0..23)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "Próximos\nAgendamentos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 3.dp)
            )
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFD27D63), CircleShape)
                    .clickable { /* Adicionar agendamento */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Adicionar", tint = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            DatePicker()
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(Modifier.height(500.dp)) {
            item {
                Card(
                    shape = RoundedCornerShape(16.dp), border = BorderStroke(
                        width = 1.dp, color = Color.LightGray
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        hours.forEach { hour ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.White)
                                        .border(
                                            BorderStroke(
                                                width = 0.6.dp, color = Color.LightGray
                                            )
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${hour}h",
                                        modifier = Modifier.padding(8.dp),
                                        fontSize = 16.sp
                                    )
                                    if (hour == 9) {
                                        Box(
                                            modifier = Modifier
                                                .width(4.dp)
                                                .fillMaxHeight()
                                                .background(Color(0xFF800980))
                                        )
                                        Text(
                                            text = "Agendamento",
                                            modifier = Modifier.padding(8.dp),
                                            fontSize = 16.sp,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
