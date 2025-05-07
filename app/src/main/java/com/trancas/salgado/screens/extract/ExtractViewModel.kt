package com.trancas.salgado.screens.extract

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.extract.classes.Atendimento
import com.trancas.salgado.screens.extract.classes.Despesa
import com.trancas.salgado.screens.extract.classes.ExpenseData
import com.trancas.salgado.screens.extract.classes.Transaction
import com.trancas.salgado.screens.extract.classes.TreatmentData
import com.trancas.salgado.service.AtendimentoService
import com.trancas.salgado.service.DespesaService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExtractViewModel : ViewModel() {

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions

    val despesa = mutableStateListOf<ExpenseData>()
    val atendimento = mutableStateListOf<TreatmentData>()

    init {
        loadExtractData()
    }

    private fun loadExtractData() {
        viewModelScope.launch {
            try {
                val apiDespesa = DespesaService.api
                val despesasResponse = apiDespesa.listarDespesas()
                despesa.clear()
                despesa.addAll(despesasResponse)

                val apiAtendimento = AtendimentoService.api
                val atendimentosResponse = apiAtendimento.listarAtendimentosPorCliente(1)
                atendimento.clear()
                atendimento.addAll(atendimentosResponse)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun calculateDailyProfit(transactions: List<Transaction>): Double {
        var entradas = 0.0
        var saidas = 0.0

        transactions.forEach { transaction ->
            when (transaction) {
                is Atendimento -> {
                    if (transaction.tipoAtendimento) entradas += transaction.valor
                }
                is Despesa -> {
                    saidas += transaction.valor
                }
            }
        }
        return entradas - saidas
    }

    fun formatarData(data: List<Int>): String {
        if (data.size < 3) return ""
        val dia = data[2].toString().padStart(2, '0')
        val mes = data[1].toString().padStart(2, '0')
        return "$dia/$mes"
    }
}