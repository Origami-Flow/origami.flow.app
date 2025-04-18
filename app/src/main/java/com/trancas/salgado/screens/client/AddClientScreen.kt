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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trancas.salgado.R
import com.trancas.salgado.ui.components.shared.CustomButton
import com.trancas.salgado.ui.components.shared.CustomInputField

@Composable
fun AddClientScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 20.dp),
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
                stringResource(R.string.clientes), fontSize = 24.sp,
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
                painter = painterResource(id = R.drawable.user_default),
                contentDescription = "Usuario padrão",
                modifier = Modifier
                    .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(100.dp))
                    .align(Alignment.Center)
                    .size(125.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.add),
                contentDescription = stringResource(R.string.camera),
                modifier = Modifier
                    .size(47.dp)
                    .align(Alignment.BottomCenter)
                    .padding(5.dp)
                    .offset(x = 47.dp, y = 5.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomInputField(
            label = stringResource(R.string.nome),
            type = "input"
        )
        Spacer(modifier = Modifier.height(12.dp))
        CustomInputField(
            label = stringResource(R.string.e_mail),
            type = "input"
        )
        Spacer(modifier = Modifier.height(12.dp))
        CustomInputField(
            label = stringResource(R.string.telefone),
            type = "input"
        )
        CustomButton(
            text = stringResource(R.string.adicionar),
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.End),
            onClick = {  },
            textColor = Color.White,
        )
    }
}

    @Preview(showBackground = true)
    @Composable
    fun AddClientScreenPreview() {
        AddClientScreen()
    }