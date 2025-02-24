package trancas.salgado.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.trancas.salgado.ui.theme.components.shared.navbar.BottomNavBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var selectedRoute by remember { mutableStateOf("home") }

    Scaffold(
        bottomBar = {
            BottomNavBar(selectedRoute = selectedRoute) { route ->
                selectedRoute = route
                navController.navigate(route)
            }
        }
    ) 
    { paddingValues ->
         Column(modifier = Modifier.padding(paddingValues)) {
             GreetingUser(name = "Paula")
         }
    }
}

@Composable
fun GreetingUser(name: String) {
    Box(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello, $name!",
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
