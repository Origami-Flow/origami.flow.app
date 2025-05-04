package com.trancas.salgado.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teste.Login
import com.trancas.salgado.R
import com.trancas.salgado.screens.MainScreen
import com.trancas.salgado.ui.theme.AppTheme
import com.trancas.salgado.ui.theme.flame_pea
import com.trancas.salgado.ui.theme.mos_green

class InitialActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    NavHost(navController = navController, startDestination = "TelaInicial") {
                        composable("TelaInicial") { InitialScreen(navController) }
                        composable("Login") { Login(navController) }
                        composable("MainScreen") { MainScreen(navController) }
                    }

                }
            }


        }
    }
}


@Composable
fun InitialScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(mos_green)

    ) {
        Image(
            painter = painterResource(id = R.drawable.img_inicio),
            contentDescription = "Paula Salgado",
            modifier = Modifier
                .fillMaxWidth()
                .height(405.dp)
        )
        Box(
            modifier = Modifier

                .height(350.dp)
                .width(400.dp)

                .padding(top = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxHeight()


            ) {

                Text(
                    text = "SALGADO\n\nTRANÇAS",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White

                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { navController.navigate("Login") },
                    colors = ButtonDefaults.buttonColors(containerColor = flame_pea),
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .size(width = 220.dp, height = 50.dp)
                ) {
                    Text(stringResource(R.string.entrar), color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    AppTheme {
        InitialScreen(navController = rememberNavController())
    }
}