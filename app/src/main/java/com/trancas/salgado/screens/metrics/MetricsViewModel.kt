package com.trancas.salgado.screens.metrics

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.metrics.classes.Metrics
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MetricsViewModel : ViewModel() {
    private val api = MetricsApi.api

    private val _metricas = MutableStateFlow(Metrics())
    val metricas: StateFlow<Metrics> = _metricas
    private val _erros = mutableStateListOf<String>()
    val erros: List<String> get() = _erros.toList()

    fun carregarMetricas(mes: Int, ano: Int) {
        viewModelScope.launch {
            val response = api.buscarMetricas(mes, ano)
            if (response.isSuccessful) {
                response.body()?.let {
                    _metricas.value = it
                } ?: run {
                    _erros.add("Resposta vazia da API")
                }
            } else {
                _erros.add("Erro ao carregar métricas: ${response.message()}")
            }
        }
    }

    fun limparErros() {
        _erros.clear()
    }
}