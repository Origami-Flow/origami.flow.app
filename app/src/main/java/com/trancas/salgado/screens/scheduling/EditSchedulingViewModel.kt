package com.trancas.salgado.screens.scheduling

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EditSchedulingViewModel : ViewModel()  {
    private val _eventTypeList = listOf("Pessoal", "Atendimento")
    var _selectedEventType by mutableStateOf("Atendimento")
        private set

    private val _serviceList = listOf("Serviço 1", "Serviço 2", "Serviço 3")
    var _selectedService by mutableStateOf("Serviço 1")
        private set

    val eventTypeList: List<String>
        get() = _eventTypeList

    val selectedEventType: String
        get() = _selectedEventType

    val serviceList: List<String>
        get() = _serviceList

    val selectedService: String
        get() = _selectedService

    fun setSelectedEventType(selectedEventType: String) {
        _selectedEventType = selectedEventType
    }

    fun setSelectedService(selectedService: String) {
        _selectedService = selectedService
    }
}