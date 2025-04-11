package com.trancas.salgado.screens.client

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trancas.salgado.R
import com.trancas.salgado.ui.components.shared.navbar.BottomNavBar


@Composable
fun EditClientScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Clientes", fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Voltar",
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.TopStart)
            )
            Image(
                painter = painterResource(id = R.drawable.user_default2),
                contentDescription = "Usuario padrão",
                modifier = Modifier
                    .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(100.dp))
                    .align(Alignment.Center)
                    .size(125.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.icon_edit),
                contentDescription = "Editar cliente",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(25.dp)
            )
        }
        Text(
            text = "Nome do cliente",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

        )
        Text(
            text = "(11) 90000-0000",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "larissa@gmail.com",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Data de nascimento", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "00/00/0000", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Ocupação", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Dev", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.width(30.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Cidade", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Blablabla", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Tipo de cabelo", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "3B", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.width(30.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Cor de cabelo", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Castanho", fontSize = 16.sp)

                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Primeira vez trançando?", fontWeight = FontWeight.Bold)
                    Text(text = "Não")
                }
                Spacer(modifier = Modifier.width(30.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Fez progressiva nos últimos 30 dias?", fontWeight = FontWeight.Bold)
                    Text(text = "Não")
                }
            }
        }
        BottomNavBar(
            selectedRoute = "edit_client",
            onItemSelected = { },
        )
    }
}



@Preview(showBackground = true)
@Composable
fun EditClientScreenPreview() {
    EditClientScreen()
}