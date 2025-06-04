package com.trancas.salgado.screens.metrics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trancas.salgado.R
import com.trancas.salgado.ui.components.metrics.MetricsBox
import com.trancas.salgado.ui.components.metrics.MetricsBoxGreen
import com.trancas.salgado.ui.theme.mos_green
import com.trancas.salgado.ui.theme.pale_pink
import org.koin.androidx.compose.koinViewModel
import java.time.YearMonth
import java.time.format.DateTimeFormatter

fun getNextMonth(month: String): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
    val yearMonthNow = YearMonth.parse(month, formatter).plusMonths(1).format(formatter)
    return yearMonthNow
}

fun getPreviousMonth(month: String): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
    val yearMonthNow = YearMonth.parse(month, formatter).minusMonths(1).format(formatter)
    return yearMonthNow
}

@Composable
fun MetricsScreen(viewModel: MetricsViewModel = koinViewModel()) {
    var month by remember {
        mutableStateOf(YearMonth.now().format(DateTimeFormatter.ofPattern("MMMM yyyy")))
    }

    val metricas by viewModel.metricas.collectAsState()

    LaunchedEffect(month) {
        val yearMonth = YearMonth.parse(month, DateTimeFormatter.ofPattern("MMMM yyyy"))
        viewModel.carregarMetricas(mes = yearMonth.monthValue, ano = yearMonth.year)
    }

    val diferencaLucro = metricas.lucroDoMesAtual - metricas.lucroDoMesPassado

    val mensagemLucro = when {
        diferencaLucro > 0 -> "Houve aumento de R$${"%.2f".format(diferencaLucro)} em relação ao mês anterior."
        diferencaLucro < 0 -> "Houve redução de R$${"%.2f".format(-diferencaLucro)} em relação ao mês anterior."
        else -> "Lucro igual ao mês anterior."
    }

    val corMensagem = when {
        diferencaLucro > 0 -> Color(0xFF1B5E20)
        diferencaLucro < 0 -> Color(0xFF86000C)
        else -> Color.DarkGray
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 3.dp),
            text = stringResource(id = R.string.metrics_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
            )

        Spacer(modifier = Modifier.height(12.dp))

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
                title = stringResource(id = R.string.metrics_sales_title),
                value = metricas.vendasDoMes.toString(),
                color = mos_green,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            MetricsBoxGreen(
                title = stringResource(id = R.string.metrics_new_clients_title),
                value = metricas.clientesNovosNoMes.toString(),
                color = mos_green,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        MetricsBoxGreen(
            title = stringResource(id = R.string.metrics_appointments_title),
            value = metricas.agendamentosDoMes.toString(),
            color = mos_green,
            isFullWidth = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MetricsBox(
                title = stringResource(id = R.string.metrics_most_done_braid_title),
                value = metricas.trancaMaisFeitaNoMes ?: "Nenhuma",
                color = pale_pink,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            MetricsBox(
                title = stringResource(id = R.string.metrics_conversion_rate_title),
                value = metricas.taxaDeClienteQueAgendaramNoMes.toString() + "%",
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
                stringResource(id = R.string.metrics_profit_comparison_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(stringResource(id = R.string.metrics_current_month_profit),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
                )
                Text("R$${metricas.lucroDoMesAtual}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row {
                Text(stringResource(id = R.string.metrics_previous_month_profit),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
                )
                Text("R$${metricas.lucroDoMesPassado}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                mensagemLucro,
                color = corMensagem,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MetricsScreenPreview() {
    MetricsScreen()
}
