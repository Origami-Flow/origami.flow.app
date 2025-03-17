import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.teste.Login
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.trancas.salgado.ui.components.login.ExibirImagemInicio


@Composable
fun TelaInicial(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFF3F522D))

    ) {
    ExibirImagemInicio()
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
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB55B49)),
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .size(width = 220.dp, height = 50.dp)
                ) {
                    Text(text = "Entrar", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}


