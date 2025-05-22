package com.trancas.salgado.screens.event

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.service.AssistantService
import com.trancas.salgado.service.ClientService
import com.trancas.salgado.service.EventService
import com.trancas.salgado.service.ServicesService
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class CreateEventViewModel: ViewModel() {

    private val _eventTypeList = listOf("PESSOAL", "ATENDIMENTO")

    private val _serviceList = mutableStateListOf<ServicesData>()

    private val _clientList = mutableStateListOf<ClientData>()

    private val _assistantList = mutableStateListOf<AssistantData>()

    private val _timeZoneId = ZoneId.of("America/Sao_Paulo")

    private var _dateTimeStart = ""

    private var _dateTimeEnd = ""

    private var trancistId = 1

    var _selectedEventType by mutableStateOf("ATENDIMENTO")
        private set

    var _selectedDate by mutableStateOf<LocalDate>(LocalDate.now(_timeZoneId))
        private set

    var _timeStart by mutableStateOf<LocalTime>(LocalTime.now(_timeZoneId))
        private set

    var _timeEnd by mutableStateOf<LocalTime>(LocalTime.now(_timeZoneId).plusHours(1))
        private set

    var _selectedIdService by mutableStateOf<Int?>(null)
        private set

    var _selectedIdClient by mutableStateOf<Int?>(null)
        private set

    var _selectedIdAssistant by mutableStateOf<Int?>(null)
        private set

    val eventTypeList: List<String>
        get() = _eventTypeList

    val selectedEventType: String
        get() = _selectedEventType

    val selectedDate: LocalDate
        get() = _selectedDate

    val timeStart: LocalTime
        get() = _timeStart

    val timeEnd: LocalTime
        get() = _timeEnd

    val serviceList: List<ServicesData>
        get() = _serviceList

    val clientList: List<ClientData>
        get() = _clientList

    val assistantList: List<AssistantData>
        get() = _assistantList

    fun setSelectedEventType(selectedEventType: String) {
        _selectedEventType = selectedEventType
    }

    fun setSelectedIdService(selectedService: Int) {
        _selectedIdService = selectedService
    }

    fun setSelectedDate(selectedDate: LocalDate) {
        _selectedDate = selectedDate
    }

    fun setDateTimeStart(timeStart: LocalTime) {
        _timeStart = timeStart
    }

    fun setDateTimeEnd(timeEnd: LocalTime) {
        _timeEnd = timeEnd
    }

    fun setSelectedIdClient(selectedClient: Int) {
        _selectedIdClient = selectedClient
    }

    fun setSelectedIdAssistant(selectedAssistant: Int?) {
        _selectedIdAssistant = selectedAssistant
    }

    private val _errors = mutableStateListOf<String>()

    val errors: List<String> get() = _errors

    fun createEvent() {
        _dateTimeStart = transformDate(selectedDate, timeStart)
        _dateTimeEnd = transformDate(selectedDate, timeEnd)

        val newEnvent = EventData(dateTimeStart = _dateTimeStart, dateTimeEnd = _dateTimeEnd, eventType = selectedEventType,
            clientId = _selectedIdClient, serviceId = _selectedIdService , trancistId = trancistId, assistantId = _selectedIdAssistant)
        val api = EventService.api
        _errors.clear()
        viewModelScope.launch {
            try {
                val response = api.post(newEnvent)
                Log.d("API", "Dados recebidos: ${response}")
            }catch (e: Exception) {
                Log.e("API", "Erro ao buscar dados: ${e.message}")
                _errors.add("Erro ao buscar dados: ${e.message}")
            }
        }
    }

    fun searchServices() {
        viewModelScope.launch {
            val api = ServicesService.api
            _errors.clear()
            try {
                val response = api.getServices()
                _serviceList.clear()
                _serviceList.addAll(response)
                Log.d("API", "Dados recebidos: ${response}")
            } catch (e: Exception) {
                Log.e("API", "Erro ao buscar dados: ${e.message}")
                _errors.add("Erro ao buscar dados: ${e.message}")
            }
        }
    }

    fun searchClients() {
        viewModelScope.launch {
            val api = ClientService.api
            _errors.clear()
            try {
                val response = api.getClients()
                _clientList.clear()
                _clientList.addAll(response)
                Log.d("API", "Dados recebidos: ${response}")
            } catch (e: Exception) {
                Log.e("API", "Erro ao buscar dados: ${e.message}")
                _errors.add("Erro ao buscar dados: ${e.message}")
            }
        }
    }

    fun searchAssistants() {
        viewModelScope.launch {
            val api = AssistantService.api
            _errors.clear()
            try {
                val response = api.getAssistants()
                _assistantList.clear()
                _assistantList.addAll(response)
                Log.d("API", "Dados recebidos: ${response}")
            } catch (e: Exception) {
                Log.e("API", "Erro ao buscar dados: ${e.message}")
                _errors.add("Erro ao buscar dados: ${e.message}")
            }
        }
    }

    fun transformDate(date: LocalDate, time: LocalTime): String {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        return date.atTime(time).format(formatter)
    }

    init {
        searchServices()
        searchClients()
        searchAssistants()
    }
}