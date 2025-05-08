package com.trancas.salgado.screens.extract

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.extract.classes.ExpenseData
import com.trancas.salgado.screens.extract.classes.TreatmentData
import com.trancas.salgado.service.AtendimentoService
import com.trancas.salgado.service.DespesaService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExtractViewModel : ViewModel() {

    val despesa = mutableStateListOf<ExpenseData>()
    val atendimento = mutableStateListOf<TreatmentData>()

    private val _transactions = MutableStateFlow<List<Any>>(emptyList())
    val transactions: StateFlow<List<Any>> = _transactions

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
                val atendimentosResponse = apiAtendimento.listarAtendimentos()
                atendimento.clear()
                atendimento.addAll(atendimentosResponse)

                Log.d("Despesa", "Despesa: ${despesasResponse}")
                Log.d("Atendimento", "Atendimento: ${atendimentosResponse}")

                _transactions.value = despesa + atendimento
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun calculateDailyProfit(transactions: List<Any>): Double {
        var entradas = 0.0
        var saidas = 0.0

        transactions.forEach { transaction ->
            when (transaction) {
                is TreatmentData -> entradas += transaction.value
                is ExpenseData -> saidas += transaction.value
            }
        }
        return entradas - saidas
    }

}