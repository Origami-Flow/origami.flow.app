package com.trancas.salgado.screens.stock

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.stock.classes.Product
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    private val api = ProductApi.api

    private val _produtos = mutableStateListOf<Product>()
    private val _erros = mutableStateListOf<String>()
    private var _chamandoApi by mutableStateOf(false)

    init {
        atualizarLista()
    }

    val erros: List<String>
        get() = _erros.toList()

    fun isChamandoApi() = (_chamandoApi)

    val lista: SnapshotStateList<Product> = _produtos


    private fun atualizarLista() {
        _erros.clear()

        viewModelScope.launch {
            _chamandoApi = true
            try {
                val resposta = api.getAllProducts()
                if (resposta.isSuccessful) {
                    resposta.body()?.let { produtos: List<Product> ->
                        _produtos.clear()
                        _produtos.addAll(produtos)
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


    fun salvarItem(item: Product) {
        if (item.nome.isBlank()) _erros.add("Nome do item não pode ser vazio")
        if (item.marca.isBlank()) _erros.add("Marca não pode ser vazio")
        if (item.valorCompra <= 0) _erros.add("Valor compra não pode ser menor ou igual a zero")
        if (item.valorVenda <= 0) _erros.add("Valor venda não pode ser menor ou igual a zero")
        if (item.quantidadeEmbalagem <= 0) _erros.add("Quantidade de embalagem não pode ser menor ou igual a zero")
        if (item.unidadeMedida.isBlank()) _erros.add("Unidade de medida não pode ser vazio")
        if (item.tipo.isBlank()) _erros.add("Tipo não pode ser vazio")
        if (_produtos.any { it.nome == item.nome && it.id != item.id }) _erros.add("Item já adicionado")
        if (_erros.isEmpty()) {
            _produtos.clear()
            viewModelScope.launch {
                _chamandoApi = true
                try {

                    if (item.id == 0) {
                        api.createProduct(item)
                    } else {
                        api.updateProduct(item.id, item)
                    }
                } catch (e: Exception) {
                    Log.e("API", "Erro ao salvar item: ${e.message}")
                    _erros.add("Erro ao salvar item: ${e.message}")
                } finally {
                    atualizarLista()
                }
            }

        }

    }

    fun removerItem(item: Product) {
        _erros.clear()
        viewModelScope.launch {
            _chamandoApi = true
            try {
                api.deleteProduct(item.id)
            } catch (e: Exception) {
                Log.e("API", "Erro ao remover item: ${e.message}")
                _erros.add("Erro ao remover item: ${e.message}")
            } finally {
                atualizarLista()
            }
        }
    }

    fun atualizarItem(item: Product) {
        _erros.clear()
        _produtos.clear()
        viewModelScope.launch {
            _chamandoApi = true
            try {
                api.updateProduct(item.id, item)
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
