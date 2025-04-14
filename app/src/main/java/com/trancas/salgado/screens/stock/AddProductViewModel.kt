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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddProductViewModel: ViewModel() {
    private val api = StockApi.api

    private val _produtos = MutableStateFlow<List<Product>>(emptyList())
    private val _erros = mutableStateListOf<String>()
    private var _chamandoApi by mutableStateOf(false)

    init {
        atualizarLista()
    }

    val erros: List<String>
        get() = _erros.toList()

    fun isChamandoApi() = (_chamandoApi)

    private fun atualizarLista() {
        _erros.clear()

        viewModelScope.launch {
            _chamandoApi = true
            try {
                val resposta = api.getAllProducts()
                if (resposta.isSuccessful) {
                    resposta.body()?.let { produtos: List<Product> ->
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


    fun salvarProduto(item: Product) {
        _erros.clear()

        if (item.nome.isBlank()) _erros.add("Nome do item não pode ser vazio")
        if (item.marca.isBlank()) _erros.add("Marca não pode ser vazio")
        if (item.valorCompra <= 0) _erros.add("Valor compra não pode ser menor ou igual a zero")
        if (item.valorVenda < 0) _erros.add("Valor venda não pode ser menor ou igual a zero")
        if (item.quantidadeEmbalagem <= 0) _erros.add("Quantidade de embalagem não pode ser menor ou igual a zero")
        if (item.unidadeMedida.isBlank()) _erros.add("Unidade de medida não pode ser vazio")
        if (item.tipo.isBlank()) _erros.add("Tipo não pode ser vazio")

        if (_erros.isEmpty()) {
            viewModelScope.launch {
                _chamandoApi = true
                try {
                    val resposta = api.createProduct(item)

                    if (resposta.isSuccessful) {
                        resposta.body()?.let { produto: Product ->
                            _produtos.value += produto
                        }
                    } else {
                        Log.e("API", "Erro na resposta: Código ${resposta.code()} - ${resposta.message()}")
                        _erros.add("Erro ao salvar item: ${resposta.message()}")
                    }

                } catch (e: Exception) {
                    Log.e("API", "Erro ao salvar item: ${e.message}")
                    _erros.add("Erro ao salvar item: ${e.message}")
                } finally {
                    atualizarLista()
                    _chamandoApi = false
                }
            }

        } else {
            Log.e("API", "Erros encontrados: ${_erros.joinToString(", ")}")
        }

    }

    fun limparErros() {
        _erros.clear()
    }
}
