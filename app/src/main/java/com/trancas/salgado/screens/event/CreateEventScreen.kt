package com.trancas.salgado.screens.event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trancas.salgado.R
import com.trancas.salgado.ui.components.shared.CustomButton
import com.trancas.salgado.ui.components.shared.CustomInputField
import com.trancas.salgado.ui.components.shared.CustomSelectInput
import com.trancas.salgado.ui.components.shared.DatePicker
import com.trancas.salgado.ui.components.shared.TimePickerInput

@Composable
fun CreateEventScreen(viewModel: CreateEventViewModel = viewModel()) {

    Box(
        Modifier.background(Color.White)
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.txt_agendamento_eventScreen),
                fontSize = 28.sp,
            )
            Spacer(modifier = Modifier.size(15.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = "",
                    modifier = Modifier.size(18.dp),
                )
                Text(
                    text = stringResource(R.string.txt_adicionar_eventScreen),
                    fontSize = 18.sp
                )
            }
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val selectEventType = viewModel.selectedEventType
                CustomSelectInput(
                    viewModel.eventTypeList,
                    viewModel.selectedEventType,
                    stringResource(R.string.txt_tipoEvento_eventScreen),
                    onOptionSelected = { selectedEventType ->
                        viewModel.setSelectedEventType(selectedEventType)

                    })
                if (selectEventType == "Atendimento") {
                    CustomSelectInput(
                        viewModel.serviceList,
                        viewModel.selectedService,
                        stringResource(R.string.txt_servicos_eventScreen),
                        onOptionSelected = { selectedService ->
                            viewModel.setSelectedService(selectedService)
                        })
                    CustomInputField(stringResource(R.string.txt_nomeCliente_eventScreen), "input")
                    CustomInputField(stringResource(R.string.txt_nomeAuxiliar_eventScreen), "input",)
                }
                DatePicker()
                TimePickerInput(stringResource(R.string.txt_horarioInicio_eventScreen))
                TimePickerInput(stringResource(R.string.txt_horarioTermino_eventScreen))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    CustomButton(stringResource(R.string.txt_salvar_eventScreen), onClick = {})
                }
            }
        }
    }
}


@Preview
@Composable
fun CreateEventScreenPreview() {
    CreateEventScreen()
}