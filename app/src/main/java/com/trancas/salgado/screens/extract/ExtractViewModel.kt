package com.trancas.salgado.screens.extract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.extract.classes.Atendimento
import com.trancas.salgado.screens.extract.classes.Despesa
import com.trancas.salgado.screens.extract.classes.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExtractViewModel : ViewModel() {

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions

    init {
        loadExtractData()
    }

    private fun loadExtractData() {
        viewModelScope.launch {
            val despesasList = listOf(
                Despesa("Conta de água", "Pagamento mensal", listOf(2025, 4, 16), 80.0),
                Despesa("Aluguel", "Imóvel comercial", listOf(2025, 4, 16), 1200.0)
            )
            val atendimentosList = listOf(
                Atendimento("João Silva", true, listOf(2025, 4, 16), 300.0),
                Atendimento("Maria Oliveira", true, listOf(2025, 4, 17), 200.0),
                Atendimento("Barbara", true, listOf(2025, 4, 18), 190.0)

            )

            _transactions.value = despesasList + atendimentosList
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