package com.trancas.salgado.ui.components.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePickerDialog(onConfirm: (TimePickerState) -> Unit, onDismiss: () -> Unit) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancelar")
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(timePickerState) }) {
                Text("Confirmar")
            }
        },
        text = {
            TimeInput(timePickerState)
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerInput(label: String, onTimeSelected: ((LocalTime, String) -> Unit)? = null) {
    val actualTime = System.currentTimeMillis()
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val formattedTime = dateFormat.format(Date(actualTime))

    var selectedTime by remember { mutableStateOf(LocalTime.now()) }
    var selectedTimeText by remember { mutableStateOf(formattedTime) }
    var showTimePicker by remember { mutableStateOf(false) }


    val focusManager = LocalFocusManager.current
    var requestDialog by remember { mutableStateOf(false) }

    LaunchedEffect(requestDialog) {
        if (requestDialog) {
            focusManager.clearFocus(force = true)
        }
    }

    OutlinedTextField(
        value = selectedTimeText,
        label = { Text(label) },
        onValueChange = { },
        readOnly = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = "Selecionar horário"
            )
        },
        textStyle = TextStyle.Default.copy(fontSize = 13.sp),
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .border(
                BorderStroke(width = 1.dp, color = Color.LightGray),
                shape = RoundedCornerShape(30)
            )
            .onFocusEvent {
                if (it.isFocused) {
                    showTimePicker = true
                    requestDialog = true
                }
            }
    )
    if (showTimePicker) {
        CustomTimePickerDialog(
            onConfirm = { timeState ->
                selectedTime = LocalTime.of(timeState.hour, timeState.minute)
                selectedTimeText = "%02d:%02d".format(timeState.hour, timeState.minute)
                onTimeSelected?.invoke(selectedTime,selectedTimeText)
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false }
        )
    }
}