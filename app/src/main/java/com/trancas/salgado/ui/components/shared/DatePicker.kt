package com.trancas.salgado.ui.components.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    selectedDateTxt: Long,
    enabled: Boolean = true,
    onDateSelected: ((LocalDate,Long
            ) -> Unit)? = null
) {
    fun Long.toBrazilianDateFormat(
        pattern: String = "dd 'de' MMMM 'de' yyyy"
    ): String {
        val date = Date(this)
        val formatter = SimpleDateFormat(
            pattern, Locale("pt", "BR")
        ).apply {
            timeZone = TimeZone.getTimeZone("GMT+3")
        }
        return formatter.format(date)
    }


    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()


    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                val localDate = Instant.ofEpochMilli(millis)
                                    .atZone(ZoneOffset.UTC)
                                    .toLocalDate()
                                onDateSelected?.invoke(localDate,millis)
                            }
                        showDatePickerDialog = false
                    }) {
                    Text(text = "Escolher data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }
    OutlinedTextField(
        value = selectedDateTxt.toBrazilianDateFormat(),
        onValueChange = { },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Calendário")
        },
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle.Default.copy(fontSize = 13.sp),
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .border(
                BorderStroke(width = 1.dp, color = Color.LightGray),
                shape = RoundedCornerShape(30)
            )
            .onFocusEvent {
                if (it.isFocused) {
                    showDatePickerDialog = true
                    focusManager.clearFocus(force = true)
                }
            },
        readOnly = true
    )
}