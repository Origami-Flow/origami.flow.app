package com.trancas.salgado.screens.stock

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.stock.classes.Product
import com.trancas.salgado.screens.stock.classes.Stock
import com.trancas.salgado.service.StockService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StockViewModel : ViewModel() {
    private val api = StockService.api

    private val _estoques = MutableStateFlow<List<Stock>>(emptyList())
    private val _produtos = MutableStateFlow<List<Product>>(emptyList())
    private val _erros = mutableStateListOf<String>()
    private var _chamandoApi by mutableStateOf(false)

    init {
        atualizarLista()
    }

    val erros: List<String>
        get() = _erros.toList()

    fun isChamandoApi() = (_chamandoApi)

    val listaEstoques: StateFlow<List<Stock>> = _estoques
    val listaProdutos: StateFlow<List<Product>> = _produtos

    private fun atualizarLista() {
        _erros.clear()

        viewModelScope.launch {
            _chamandoApi = true
            try {
                val respostaEstoques = api.getAllStocks()
                if (respostaEstoques.isSuccessful) {
                    respostaEstoques.body()?.let { estoques: List<Stock> ->
                        _estoques.value = estoques
                    }
                }

                val respostaProdutos = api.getAllProducts()
                if (respostaProdutos.isSuccessful) {
                    respostaProdutos.body()?.let { produtos: List<Product> ->
                        _produtos.value = produtos
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

    fun removerEstoque(item: Stock) {
        _erros.clear()
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


    fun removerProduto(produto: Product) {
        _erros.clear()
        viewModelScope.launch {
            _chamandoApi = true
            try {
                produto.id?.let { api.deleteProduct(it) }
                atualizarLista()
            } catch (e: Exception) {
                Log.e("API", "Erro ao remover item: ${e.message}")
                _erros.add("Erro ao remover item: ${e.message}")
            } finally {
                _chamandoApi = false
            }
        }
    }

    fun buscarPorNome(query: String) {
        _erros.clear()
        viewModelScope.launch {
            _chamandoApi = true
            try {
                val resposta = api.getProductsByName(query)
                if (resposta.isSuccessful) {
                    resposta.body()?.let { produtos: List<Product> ->
                        _produtos.value = produtos
                    }
                }
            } catch (e: Exception) {
                Log.e("API", "Erro ao buscar item: ${e.message}")
                _erros.add("Erro ao remover item: ${e.message}")
            } finally {
                _chamandoApi = false
            }
        }
    }

    private suspend fun buscarEstoquePorid(id: Int): Stock? {
        _erros.clear()

        return try {
            _chamandoApi = true
            val resposta = api.getStockById(id)
            if (resposta.isSuccessful) {
                resposta.body()?.let { estoque: Stock ->
                    _estoques.value = _estoques.value.map {
                        if (it.id == estoque.id) {
                            estoque
                        } else {
                            it
                        }
                    }
                    estoque
                }
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("API", "Erro ao buscar estoque: ${e.message}")
            _erros.add("Erro ao buscar estoque: ${e.message}")
            null
        } finally {
            _chamandoApi = false
        }
    }

    private fun atualizarQuantidade(id: Int, quantidade: Int) {
        _erros.clear()

        viewModelScope.launch {
            _chamandoApi = true

            try {
                val resposta = api.updateStock(id, quantidade)

                Log.d("API", "Resposta da API: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    resposta.body()?.let { estoque: Stock ->
                        _estoques.value = _estoques.value.map {
                            if (it.id == estoque.id) {
                                estoque
                            } else {
                                it
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("API", "Erro ao atualizar quantidade: ${e.message}")
            }

        }
    }

    fun atualizarQuantidadeERecarregar(
        stockId: Int,
        quantidade: Int,
        onUpdated: (Stock?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                atualizarQuantidade(stockId, quantidade)
                val atualizado = buscarEstoquePorid(stockId)
                onUpdated(atualizado)
            } catch (e: Exception) {
                Log.e("API", "Erro ao atualizar: ${e.message}")
            }
        }
    }

    fun limparErros() {
        _erros.clear()
    }
}