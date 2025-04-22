package com.trancas.salgado.ui.components.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import com.trancas.salgado.screens.schedule.classes.Event
import com.trancas.salgado.ui.theme.flame_pea
import com.trancas.salgado.ui.theme.green20
import com.trancas.salgado.ui.theme.pale_pink
import com.trancas.salgado.ui.theme.purple20
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

@Composable
fun EventCard(item: Event) {
    val tipoCor = when (item.tipo) {
        "Concluído" -> green20
        "Pessoal" -> flame_pea
        "Atendimento" -> purple20
        else -> Color.Gray
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = pale_pink)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(6.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(4.dp)
                        .background(tipoCor)
                        .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(item.nome, fontWeight = FontWeight.Bold)
                item.cliente?.let { Text(it) }
                item.telefone?.let { Text(it) }
            }
            Text(
                DateTimeFormatter.ofPattern("HH:mm").format(item.horario),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 17.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}