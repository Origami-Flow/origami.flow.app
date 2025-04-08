package com.trancas.salgado.screens.Finances

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.service.SalgadoApi
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class FinancesViewModel: ViewModel() {

    private val api = SalgadoApi.api
    private var _date = LocalDate.now()
    var month: Int = _date.monthValue
    var monthText: String = DateTimeFormatter.ofPattern("MMMM", Locale.getDefault()).format(_date).replaceFirstChar { it.uppercase() }
    var year: Int = _date.year

    private val _errors = mutableStateListOf<String>()

    private val _profit = mutableStateOf(0.0)
    init {
        updateProfit()
    }

    val errors: List<String> get() = _errors

    val profit: Double get() = _profit.value

    fun updateProfit(){
        _errors.clear()
        viewModelScope.launch {
            try {
                val response = api.getFinances(month, year)
                _profit.value = response.profitMonth
                Log.d("API", "Dados recebidos: ${response.profitMonth}")
            } catch (e: Exception) {
                Log.e("API", "Erro ao buscar dados: ${e.message}")
                _errors.add("Erro ao buscar dados: ${e.message}")
            }
        }
    }
}