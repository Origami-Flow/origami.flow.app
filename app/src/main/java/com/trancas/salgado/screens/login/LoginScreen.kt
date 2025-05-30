package com.trancas.salgado.screens.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.trancas.salgado.R
import com.trancas.salgado.ui.MainActivity
import com.trancas.salgado.ui.theme.flame_pea
import org.koin.androidx.compose.koinViewModel

@Composable
fun Login(navController: NavController, viewModel: LoginViewModel = koinViewModel()) {
    val email = viewModel.email
    val senha = viewModel.senha
    val loginResult = viewModel.loginResult

    LaunchedEffect(loginResult) {
        val context = navController.context

        when (loginResult) {
            is LoginResult.Success -> {
                Toast.makeText(context, "Login bem-sucedido", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                if (context is android.app.Activity) context.finish()
            }

            is LoginResult.Failure -> {
                Toast.makeText(context, loginResult.message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    )
    {
        Image(
            painter = painterResource(id = R.drawable.img_login),
            contentDescription = stringResource(R.string.img_login),
            modifier = Modifier
                .height(346.dp)
                .width(390.dp)
                .offset(x = 70.dp, y = -165 .dp)

        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(

                    text = stringResource(R.string.bem_vindo),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .height(100.dp)

                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { viewModel.email = it },
                    label = { Text(stringResource(R.string.e_mail)) },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = senha,
                    onValueChange = { viewModel.senha = it },
                    label = { Text(stringResource(R.string.senha)) },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Senha Icon") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
                Button(
                    onClick = {
                        viewModel.loginApp()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = flame_pea),
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .size(width = 220.dp, height = 50.dp)
                ) {
                    Text(stringResource(R.string.entrar), color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    Login(navController)
}


