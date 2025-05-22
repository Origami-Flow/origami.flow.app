package com.trancas.salgado.screens.event

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.service.AssistantService
import com.trancas.salgado.service.ScheduleService
import com.trancas.salgado.service.ServicesService
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class EditEventViewModel : ViewModel()  {

    private val _timeZoneId = ZoneId.of("America/Sao_Paulo")

    private var _eventId by mutableStateOf<Int>(0)
        private set

    private var _selectedDate by mutableStateOf<LocalDate>(LocalDate.now(_timeZoneId))
        private set

    private var _timeStart by mutableStateOf<LocalTime>(LocalTime.now(_timeZoneId))
        private set

    private var _timeEnd by mutableStateOf<LocalTime>(LocalTime.now(_timeZoneId).plusHours(1))
        private set

    private val _eventTypeList = listOf("PESSOAL", "ATENDIMENTO")

    private val _serviceList = mutableStateListOf<ServicesData>()

    private val _assistantList = mutableStateListOf<AssistantData>()

    var _selectedEventType by mutableStateOf("ATENDIMENTO")
        private set

    val eventTypeList: List<String>
        get() = _eventTypeList

    val selectedEventType: String
        get() = _selectedEventType

    var _selectedIdService by mutableStateOf<Int?>(null)
        private set

    private var _serviceName by mutableStateOf("")
        private set

    var _selectedIdAssistant by mutableStateOf<Int?>(null)
        private set

    private var _assistantName by mutableStateOf("")
        private set

    val serviceList: List<ServicesData>
        get() = _serviceList

    val assistantList: List<AssistantData>
        get() = _assistantList

    val serviceName: String
        get() = _serviceName

    val assistantName: String
        get() = _assistantName

    val selectedDate: LocalDate
        get() = _selectedDate

    val timeStart: LocalTime
        get() = _timeStart

    val timeEnd: LocalTime
        get() = _timeEnd

    val eventId: Int
        get() = _eventId

    fun setEventId(eventId: Int) {
        _eventId = eventId
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

    fun setSelectedEventType(selectedEventType: String) {
        _selectedEventType = selectedEventType
    }

    fun setSelectedIdService(selectedService: Int) {
        _selectedIdService = selectedService
    }

    fun setSelectedIdAssistant(selectedAssistant: Int?) {
        _selectedIdAssistant = selectedAssistant
    }

    fun setServiceName(serviceName: String) {
        _serviceName = serviceName
    }

    fun setAssistantName(auxiliarName: String) {
        _assistantName = auxiliarName
    }

    private val _errors = mutableStateListOf<String>()

    val errors: List<String> get() = _errors

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

    fun searchEvent(id: Int) {
        viewModelScope.launch {
            val api = ScheduleService.api
            _errors.clear()
            try {
                val response = api.getEventoById(id)
                response.body()?.let { event ->
                    _eventId = event.id

                    _selectedEventType = event.tipoEvento
                    if(event.tipoEvento.equals("ATENDIMENTO", true)) {
                        _selectedIdService = event.servico.id
                        _serviceName = event.servico.nome

                        _selectedIdAssistant = event.auxiliar?.id
                        _assistantName = event.auxiliar?.nome ?: ""
                    }
                    _selectedDate = LocalDateTime.parse(event.dataHoraInicio).toLocalDate()
                    _timeStart = LocalDateTime.parse(event.dataHoraInicio).toLocalTime()
                    _timeEnd = LocalDateTime.parse(event.dataHoraTermino).toLocalTime()
                    Log.d("API", "Dados recebidos: ${response}")
                }
            } catch (e: Exception) {
                Log.e("API", "Erro ao buscar dados: ${e.message}")
                _errors.add("Erro ao buscar dados: ${e.message}")
            }
        }
    }

    fun editEvent() {
        val dateTimeStart = transformDate(_selectedDate, _timeStart)
        val dateTimeEnd = transformDate(_selectedDate, _timeEnd)

        val newEvent = EventData(
            id = eventId,
            dateTimeStart = dateTimeStart,
            dateTimeEnd = dateTimeEnd,
            eventType = _selectedEventType,
            clientId = _selectedIdService,
            serviceId = _selectedIdService,
            trancistId = null,
            assistantId = _selectedIdAssistant
        )

        val api = ScheduleService.api
        _errors.clear()
        viewModelScope.launch {
            try {
                Log.d("API","${newEvent}")
                val response = api.updateEvento(eventId, newEvent)
                Log.d("API", "Dados recebidos: ${response}")
            } catch (e: Exception) {
                Log.e("API", "Erro ao buscar dados: ${e.message}")
                _errors.add("Erro ao buscar dados: ${e.message}")
            }
        }
    }

    fun deletEvent(Id: Int) {
        val api = ScheduleService.api
        _errors.clear()
        viewModelScope.launch {
            try {
                val response = api.deleteEvento(Id)
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
        searchAssistants()
    }
}