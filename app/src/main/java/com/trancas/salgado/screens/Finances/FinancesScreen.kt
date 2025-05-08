package com.trancas.salgado.screens.finances

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.trancas.salgado.R
import com.trancas.salgado.ui.theme.flame_pea


@Composable
fun FinancesScreen(viewModel: FinancesViewModel = viewModel(), navController: NavController) {

    BoxWithConstraints {
        val screenHeight = this.maxHeight
        Box(
            Modifier.background(flame_pea)
                .fillMaxWidth()
                .height(screenHeight * 0.24f)
        )
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(top = 70.dp, start = 18.dp),
            text = stringResource(R.string.txt_ola_financesScreen, "Usuário"),
            fontSize = 28.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(50.dp))
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier =  Modifier
                    .width(320.dp)
                    .height(170.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(24.dp))
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(Color.White)
            ) {
                Column (
                    modifier = Modifier.fillMaxHeight(),
                ) {
                    Text(
                        modifier = Modifier.padding(top = 25.dp, start = 25.dp, bottom = 5.dp),
                        text = stringResource(R.string.txt_lucroMes_financesScreen, viewModel.monthText),
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 10.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                    Row {
                        Text(
                            modifier = Modifier.padding(top = 28.dp, start = 25.dp),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            text = stringResource(R.string.txt_valorLucro_financesScreen, viewModel.profit)
                        )
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Button(
                modifier =  Modifier
                    .width(320.dp)
                    .height(90.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = flame_pea),
                onClick = { }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .clickable {
                            navController.navigate("extractScreen")
                        },
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.file_icon),
                        contentDescription = "Icone de arquivo",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = stringResource(R.string.txt_botaoExtrato_financesScreen),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
    }
}