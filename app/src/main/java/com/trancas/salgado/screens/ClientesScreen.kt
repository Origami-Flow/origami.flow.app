package com.trancas.salgado.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trancas.salgado.R
import com.trancas.salgado.ui.components.shared.AddButton
import com.trancas.salgado.ui.theme.flame_pea
import com.trancas.salgado.ui.theme.pale_pink

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClientListScreen()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ClientListScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp, start = 20.dp, end = 20.dp),
    ) {
        TopBar()
        SearchBar()
        ClientList()
    }
}

@Composable
fun TopBar() {
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
        AddButton(onClick = { /* Ação ao clicar */ })
    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Pesquisar") },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.iconpesquisa),
                contentDescription = "Search",
                modifier = Modifier.size(24.dp)

            )
        },

        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = pale_pink,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = pale_pink,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, end = 3.dp, start = 3.dp, bottom = 16.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(
                pale_pink
            )
            .border(BorderStroke(width = 0.dp, color = Color.Transparent))
    )
}

@Composable
fun ClientList() {
    val clients = listOf("Larissa", "Larissa", "Larissa", "Larissa", "Larissa")
    Column(modifier = Modifier.padding(horizontal = 3.dp)) {
        clients.forEachIndexed { index, name ->
            ClientCard(name)
            if (index < clients.size - 1) {
                Spacer(modifier = Modifier.height(8.dp)) // Adiciona espaçamento entre os cards
            }
        }
    }
}

@Composable
fun ClientCard(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(116.dp)
            .padding(vertical = 8.dp)
            .background(Color(0XFFB55B49), RoundedCornerShape(16.dp))
            .padding(16.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(4.dp)) // Pequeno espaço entre a imagem e o nome
            Text(name, fontWeight = FontWeight.Bold, color = Color.White)
        }
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = { /* Ação ao clicar */ },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            ) {
                Text("Próximo Agendamento", color = Color.Black, fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = painterResource(id = R.drawable.setalatral),
            contentDescription = "Next",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ClienteScreen() {
    ClientListScreen()
}



