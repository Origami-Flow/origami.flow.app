package com.trancas.salgado.ui.components.metrics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trancas.salgado.ui.theme.pale_pink

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
    }
}