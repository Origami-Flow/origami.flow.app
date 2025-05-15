package com.trancas.salgado.ui.components.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(50.dp)
            .width(150.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B4F3F)),
        shape = MaterialTheme.shapes.medium
    ) {

        Text(text = text, fontSize = 16.sp, color = textColor)
    }
}