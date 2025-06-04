package com.trancas.salgado.screens.schedule

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.schedule.classes.Event
import com.trancas.salgado.service.ScheduleService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeeklyScheduleViewModel(private val api:ScheduleService): ViewModel() {


    private val _eventos = MutableStateFlow<List<Event>>(emptyList())
    val eventos: StateFlow<List<Event>> = _eventos

    private val _erros = MutableStateFlow<List<String>>(emptyList())
    val erros: StateFlow<List<String>> = _erros

    private var _chamandoApi by mutableStateOf(false)
    fun isChamandoApi() = (_chamandoApi)


    fun buscarEventoPorData(data: String) {
        limparErros()
        val errorList = mutableListOf<String>()

        if (data.isBlank()) {
            errorList.add("A data não pode estar vazia")
        }
        if (errorList.isEmpty()) {
            viewModelScope.launch {
                _chamandoApi = true
                try {
                    val response = api.getEventosByData(data)
                    if (response.isSuccessful) {
                        val eventos = response.body()
                        _eventos.value = eventos ?: emptyList()
                    } else {
                        Log.e("API", "Erro ao buscar eventos: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("API", "Exceção ao buscar eventos: ${e.message}")
                } finally {
                    if (errorList.isNotEmpty()) {
                        _erros.value = errorList
                    }
                    _chamandoApi = false
                }
            }

        } else {
            Log.e("API", "Erros encontrados: ${errorList.joinToString(", ")}")
        }

    }

    private fun limparErros() {
        _erros.value = emptyList()
    }
}