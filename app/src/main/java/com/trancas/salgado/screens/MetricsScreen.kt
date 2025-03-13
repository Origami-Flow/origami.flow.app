package com.trancas.salgado.screens

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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

@RequiresApi(Build.VERSION_CODES.O)
fun getNextMonth(month: String): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
    val yearMonthNow = YearMonth.parse(month, formatter).plusMonths(1).format(formatter)
    return yearMonthNow
}

@RequiresApi(Build.VERSION_CODES.O)
fun getPreviousMonth(month: String): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
    val yearMonthNow = YearMonth.parse(month, formatter).minusMonths(1).format(formatter)
    return yearMonthNow
}

@Composable
fun MetricsScreen() {
    var month by remember {
        mutableStateOf(YearMonth.now().format(DateTimeFormatter.ofPattern("MMMM yyyy")))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 45.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
            text = "Métricas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
            )

        Spacer(modifier = Modifier.height(20.dp))

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
                onClick = { month = getNextMonth(month) }
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
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .background(Color(0XFFC3DDFD), RoundedCornerShape(10.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Lucro: Comparação de Meses",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text("Mês atual: ",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
                )
                Text("R$00,00",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row {
                Text("Mês anterior: ",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
                )
                Text("R$00,00",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Houve redução de R$ em relação ao mês anterior.",
                color = Color(0xFF86000C),
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold
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
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .background(color, RoundedCornerShape(10.dp))
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
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
                fontWeight = FontWeight.W400
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
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .background(color, RoundedCornerShape(10.dp))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = pale_pink
            )

            Text(
                text = value,
                fontSize = 16.sp,
                color = pale_pink,
                fontWeight = FontWeight.W400
            )
        }
    }}

@Preview(showBackground = true)
@Composable
fun MetricsScreenPreview() {
    MetricsScreen()
}
