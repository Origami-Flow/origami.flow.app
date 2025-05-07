package com.trancas.salgado.screens.client

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.trancas.salgado.R
import com.trancas.salgado.ui.components.shared.CustomButton
import com.trancas.salgado.ui.components.shared.navbar.BottomNavBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditClientScreen(
    navController: NavController, viewModel: ClientViewModel = koinViewModel(),clientId: Int
) {
   val isEditing = remember { mutableStateOf(false) }
//    LaunchedEffect(Unit) {
//        viewModel.getClient(clientId)
//    }
    //    var nome = viewModel.nome
//    var email = viewModel.email
//    var telefone = viewModel.telefone
//    var tipoCabelo = viewModel.tipoCabelo
//    var corCabelo = viewModel.corCabelo
//    var ocupacao = viewModel.ocupacao
//    var dataNascimento = viewModel.dataNascimento

    val nome = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val telefone = remember { mutableStateOf("") }
    val tipoCabelo = remember { mutableStateOf("") }
    val corCabelo = remember { mutableStateOf("") }
    val ocupacao = remember { mutableStateOf("") }
    val cidade = remember { mutableStateOf("") }
    val primeiraVezTrancando = remember { mutableStateOf("") }
    val fezProgressiva = remember { mutableStateOf("") }
    val dataNascimento = remember { mutableStateOf("") }
    val scrollState = rememberScrollState();

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(top = 25.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
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
                contentDescription = stringResource(R.string.voltar),
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.TopStart)
            )
            Image(
                painter = painterResource(id = R.drawable.user_default2),
                contentDescription = stringResource(R.string.usuario_padrao),
                modifier = Modifier
                    .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(100.dp))
                    .align(Alignment.Center)
                    .size(100.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.icon_edit),
                contentDescription = stringResource(R.string.editar_cliente),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(25.dp)
                    .clickable { isEditing.value = !isEditing.value }
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = nome.value,
                onValueChange = { nome.value = it },
                label = { Text(stringResource(R.string.nome_do_cliente)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF0F0F0),
                    unfocusedContainerColor = Color(0xFFF0F0F0)
                ),
                enabled = isEditing.value
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = telefone.value,
                onValueChange = { telefone.value = it },
                label = { Text(stringResource(R.string.telefone)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF0F0F0),
                    unfocusedContainerColor = Color(0xFFF0F0F0)
                ),
                enabled = isEditing.value
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(stringResource(R.string.e_mail)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF0F0F0),
                    unfocusedContainerColor = Color(0xFFF0F0F0)
                ),
                enabled = isEditing.value
            )
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(R.string.data_de_nascimento), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = dataNascimento.value,
                    onValueChange = { dataNascimento.value = it },
                    label = {  },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF0F0F0),
                        unfocusedContainerColor = Color(0xFFF0F0F0)
                    ),
                    enabled = isEditing.value
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.ocupacao), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = ocupacao.value,
                        onValueChange = { ocupacao.value = it },
                        label = { Text(stringResource(R.string.ocupacao)) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF0F0F0),
                            unfocusedContainerColor = Color(0xFFF0F0F0)
                        ),
                        enabled = isEditing.value
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.cidade), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = cidade.value,
                        onValueChange = { cidade.value = it },
                        label = { Text(stringResource(R.string.cidade)) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF0F0F0),
                            unfocusedContainerColor = Color(0xFFF0F0F0)
                        ),
                        enabled = isEditing.value
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.tipo_de_cabelo), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = tipoCabelo.value,
                        onValueChange = { tipoCabelo.value = it },
                        label = { Text(stringResource(R.string.tipo_de_cabelo)) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF0F0F0),
                            unfocusedContainerColor = Color(0xFFF0F0F0)
                        ),
                                enabled = isEditing.value
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.cor_de_cabelo), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = corCabelo.value,
                        onValueChange = { corCabelo.value = it },
                        label = { Text(stringResource(R.string.cor_de_cabelo)) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF0F0F0),
                            unfocusedContainerColor = Color(0xFFF0F0F0)
                        ),
                        enabled = isEditing.value
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.primeira_vez_trancando), fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = primeiraVezTrancando.value,
                        onValueChange = { primeiraVezTrancando.value = it },
                        label = { },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF0F0F0),
                            unfocusedContainerColor = Color(0xFFF0F0F0)
                        ),
                        enabled = isEditing.value
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.fez_progressiva), fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = fezProgressiva.value,
                        onValueChange = { fezProgressiva.value = it },
                        label = { },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF0F0F0),
                            unfocusedContainerColor = Color(0xFFF0F0F0)
                        ),
                        enabled = isEditing.value
                    )
                }
            }
            CustomButton(
                text = stringResource(R.string.salvar),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {

                },
                textColor = Color.White,
            )
        }

        BottomNavBar(
            selectedRoute = "edit_client",
            onItemSelected = { },
        )


    }
}

