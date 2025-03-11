package com.trancas.salgado.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trancas.salgado.R
import com.trancas.salgado.ui.theme.mos_green
import com.trancas.salgado.ui.theme.pale_pink
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun MetricsScreen() {
    var month by remember {
        mutableStateOf(YearMonth.now().format(DateTimeFormatter.ofPattern("MMMM")))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Métricas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { month = getPreviousMonth(month) }) {
                Image(
                    painter = painterResource(id = R.drawable.group65),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }
            Text(
                text = month,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
            IconButton(
                onClick = {
                month = YearMonth.parse(month,
                    DateTimeFormatter.ofPattern("MMMM"))
                    .plusMonths(1)
                    .format(DateTimeFormatter
                        .ofPattern("MMMM")
                    )
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.group66),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MetricsBoxGreen(
                title = "Vendas",
                value = "10",
                color = mos_green,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            MetricsBoxGreen(
                title = "Novos clientes",
                value = "10",
                color = mos_green,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        MetricsBoxGreen(
            title = "Agendamentos",
            value = "10",
            color = mos_green,
            isFullWidth = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MetricsBox(
                title = "Trança mais realizada",
                value = "Twist",
                color = pale_pink,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            MetricsBox(
                title = "Taxa de Conversão em Agendamentos",
                value = "68%",
                color = pale_pink,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0XFFC3DDFD), RoundedCornerShape(10.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Lucro: Comparação de Meses",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Mês atual: R$00,00",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text("Mês anterior: R$00,00",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Houve redução de R$ em relação ao mês anterior.",
                color = Color(0xFF86000C),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun MetricsBox(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    color: Color,
    isFullWidth: Boolean = false
) {
    Box(
        modifier = modifier
            .then(if (isFullWidth) Modifier.fillMaxWidth() else Modifier.width(158.dp))
            .height(160.dp)
            .background(color, RoundedCornerShape(10.dp))
//            .graphicsLayer(
//                shadowElevation = 4.dp.value,
//                shape = RoundedCornerShape(10.dp),
//                clip = false,
//                translationX = -2.dp.value,
////                alpha = 0.5f
//            )
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = mos_green,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = value,
                fontSize = 18.sp,
                color = mos_green,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun MetricsBoxGreen(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    color: Color,
    isFullWidth: Boolean = false
) {
    Box(
        modifier = modifier
            .then(if (isFullWidth) Modifier.fillMaxWidth() else Modifier.width(150.dp))
            .height(80.dp)
            .background(color, RoundedCornerShape(10.dp))
//            .graphicsLayer(
//                shadowElevation = 6.dp.value,
//                shape = RoundedCornerShape(10.dp),
//                clip = false,
//                translationX = -2.dp.value,
//            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = pale_pink
            )

            Text(
                text = value,
                fontSize = 16.sp,
                color = pale_pink
            )
        }
    }}

    fun getNextMonth(month: String): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM")
        val yearMonth = YearMonth.parse(month, formatter)
        return yearMonth.plusMonths(1).format(formatter)
    }

    fun getPreviousMonth(month: String): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM")
        val yearMonth = YearMonth.parse(month, formatter)
        return yearMonth.minusMonths(1).format(formatter)
    }


@Preview(showBackground = true)
@Composable
fun MetricsScreenPreview() {
    MetricsScreen()
}
