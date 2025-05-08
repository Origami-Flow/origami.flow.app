package com.trancas.salgado.ui.components.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trancas.salgado.ui.theme.flame_pea
import com.trancas.salgado.ui.theme.pale_pink
import java.time.LocalDate
import java.util.Locale

@Composable
fun BubbleDay(date: LocalDate, isSelected: Boolean, onClick: () -> Unit) {
    val dayNumber = date.dayOfMonth.toString()
    val dayLabel = date.dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, Locale("pt", "BR"))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .height(80.dp)
                .width(40.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 50.dp,
                        topEnd = 50.dp,
                    )
                )
                .background(if (isSelected) pale_pink else Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    text = dayNumber,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 18.sp,
                    color = if (isSelected) flame_pea else Color.Black
                )
                Text(
                    text = dayLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isSelected) flame_pea else Color.Black,
                    fontSize = 12.sp
                )
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) flame_pea else pale_pink)
                )
            }
        }
    }
}
