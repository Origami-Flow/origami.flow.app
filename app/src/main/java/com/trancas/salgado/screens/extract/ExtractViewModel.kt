package com.trancas.salgado.screens.extract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.extract.classes.Atendimento
import com.trancas.salgado.screens.extract.classes.Despesa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExtractViewModel : ViewModel() {

    private val _despesas = MutableStateFlow<List<Despesa>>(emptyList())

    private val _atendimentos = MutableStateFlow<List<Atendimento>>(emptyList())

    private val _groupedTransactions = MutableStateFlow<Map<String, List<Any>>>(emptyMap())
    val groupedTransactions: StateFlow<Map<String, List<Any>>> = _groupedTransactions

    init {
        loadExtractData()
    }

    private fun loadExtractData() {
        viewModelScope.launch {
            val despesasList = listOf(
                Despesa("Conta de água", "Pagamento mensal", 80.0, listOf(2025, 4, 16)),
                Despesa("Aluguel", "Imóvel comercial", 1200.0, listOf(2025, 4, 16))
            )
            val atendimentosList = listOf(
                Atendimento("João Silva", true, listOf(2025, 4, 16), 300.0),
                Atendimento("Maria Oliveira", true, listOf(2025, 4, 17), 200.0)
            )

            _despesas.value = despesasList
            _atendimentos.value = atendimentosList

            val allTransactions = (despesasList + atendimentosList)
                .sortedByDescending { transaction ->
                    when (transaction) {
                        is Despesa -> transaction.data.joinToString("") { it.toString().padStart(2, '0') }
                        is Atendimento -> transaction.data.joinToString("") { it.toString().padStart(2, '0') }
                        else -> ""
                    }
                }
                .groupBy {
                    when (it) {
                        is Despesa -> formatarData(it.data)
                        is Atendimento -> formatarData(it.data)
                        else -> ""
                    }
                }

            _groupedTransactions.value = allTransactions
        }
    }

    fun calculateDailyProfit(transactions: List<Any>): Double {
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