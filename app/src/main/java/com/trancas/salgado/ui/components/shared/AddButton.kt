package com.trancas.salgado.ui.components.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.trancas.salgado.ui.theme.flame_pea

@Composable
fun AddButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(color = flame_pea, CircleShape)
            .clickable { onClick },
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Adicionar", tint = Color.White)
    }
}