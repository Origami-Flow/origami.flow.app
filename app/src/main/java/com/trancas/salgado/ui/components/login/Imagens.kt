package com.trancas.salgado.ui.components.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.trancas.salgado.R

@Composable
fun ExibirImagemInicio() {
    Image(
        painter = painterResource(id = R.drawable.img_inicio),
        contentDescription = "Paula Salgado",
        modifier = Modifier
            .fillMaxWidth()
            .height(405.dp)
    )
}

@Composable
fun ExibirImagemLogin() {
    Image(
        painter = painterResource(id = R.drawable.img_login),
        contentDescription = "Imagem login",
        modifier = Modifier
            .height(346.dp)
            .width(390.dp)
            .offset(x = 70.dp, y = -165 .dp)

    )
}