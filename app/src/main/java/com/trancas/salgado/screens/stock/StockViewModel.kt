package com.trancas.salgado.screens.stock

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.stock.classes.Stock
import kotlinx.coroutines.launch

class StockViewModel : ViewModel() {
    private val api = StockApi.api

    private val _estoques = mutableStateListOf<Stock>()
    private val _erros = mutableStateListOf<String>()
    private var _chamandoApi by mutableStateOf(false)

    init {
        atualizarLista()
    }

    val erros: List<String>
        get() = _erros.toList()

    fun isChamandoApi() = (_chamandoApi)

    val lista: List<Stock>
        get() {
            return _estoques.toList()
        }

    private fun atualizarLista() {
        _erros.clear()

        viewModelScope.launch {
            _chamandoApi = true
            try {
                val resposta = api.getAllStocks()
                if (resposta.isSuccessful) {
                    resposta.body()?.let { estoques: List<Stock> ->
                        _estoques.clear()
                        _estoques.addAll(estoques)
                    }
                }
            } catch (e: Exception) {
                Log.e("API", "Erro ao buscar dados: ${e.message}")
                _erros.add("Erro ao buscar dados: ${e.message}")
            } finally {
                _chamandoApi = false
            }
        }
    }

    fun estoquePorId(id: Int): Stock? {
        return _estoques.find { it.produto.id == id }
    }


    fun removerEstoque(item: Stock) {
        _erros.clear()
        _estoques.clear()
        viewModelScope.launch {
            _chamandoApi = true
            try {
                api.deleteStock(item.id)
            } catch (e: Exception) {
                Log.e("API", "Erro ao remover item: ${e.message}")
                _erros.add("Erro ao remover item: ${e.message}")
            } finally {
                atualizarLista()
            }
        }
    }

    fun atualizarEstoque(item: Stock) {
        _erros.clear()
        _estoques.clear()
        viewModelScope.launch {
            _chamandoApi = true
            try {
                api.updateStock(item.id, item)
            } catch (e: Exception) {
                Log.e("API", "Erro ao atualizar item: ${e.message}")
                _erros.add("Erro ao remover item: ${e.message}")
            } finally {
                atualizarLista()
            }
        }
    }

    fun limparErros() {
        _erros.clear()
    }
}