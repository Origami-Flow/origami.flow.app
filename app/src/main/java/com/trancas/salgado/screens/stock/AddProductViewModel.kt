package com.trancas.salgado.screens.stock

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.stock.classes.Product
import com.trancas.salgado.service.StockService
import com.trancas.salgado.ui.utils.criarRequestBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddProductViewModel(private val api: StockService): ViewModel() {

    private val _produtos = MutableStateFlow<List<Product>>(emptyList())
    private val _erros = MutableStateFlow<List<String>>(emptyList())
    private var _chamandoApi by mutableStateOf(false)
    var saveResult by mutableStateOf<Result<Unit>?>(null)
        private set

    init {
        atualizarLista()
    }

    val erros: StateFlow<List<String>> = _erros


    fun isChamandoApi() = (_chamandoApi)

    private fun atualizarLista() {
        limparErros()
        val errorList = mutableListOf<String>()

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
                errorList.add("Erro ao buscar dados: ${e.message}")
            } finally {
                if (errorList.isNotEmpty()) {
                    _erros.value = errorList
                }
                _chamandoApi = false
            }
        }
    }


    fun salvarProduto(item: Product) {
        limparErros()
        val errorList = mutableListOf<String>()

        if (item.nome.isBlank()) errorList.add("Nome do item não pode ser vazio")
        if (item.marca.isBlank()) errorList.add("Marca não pode ser vazio")
        if (item.valorCompra <= 0) errorList.add("Valor compra não pode ser menor ou igual a zero")
        if (item.valorVenda < 0) errorList.add("Valor venda não pode ser menor ou igual a zero")
        if (item.quantidadeEmbalagem <= 0) errorList.add("Quantidade de embalagem não pode ser menor ou igual a zero")
        if (item.unidadeMedida.isBlank()) errorList.add("Unidade de medida não pode ser vazio")
        if (item.tipo.isBlank()) errorList.add("Tipo não pode ser vazio")

        if (errorList.isEmpty()) {
            viewModelScope.launch {
                _chamandoApi = true
                try {
                    val nome = criarRequestBody(item.nome)
                    val marca = criarRequestBody(item.marca)
                    val valorCompra = criarRequestBody(item.valorCompra.toString())
                    val valorVenda = criarRequestBody(item.valorVenda.toString())
                    val quantidadeEmbalagem = criarRequestBody(item.quantidadeEmbalagem.toString())
                    val unidadeMedida = criarRequestBody(item.unidadeMedida)
                    val tipo = criarRequestBody(item.tipo)
                    val quantidade = criarRequestBody(item.quantidade.toString())
                    val idSalao = criarRequestBody(item.salaoId.toString())

                    val resposta = api.createProduct(
                        nome, marca, valorCompra, valorVenda,
                        quantidadeEmbalagem, unidadeMedida, tipo,
                        quantidade, idSalao, item.imagem
                    )
                    Log.d("API", "Resposta da API: ${resposta.body()} - Código: ${resposta.code()}")
                    if (resposta.isSuccessful) {
                        saveResult = Result.success(Unit)
                        resposta.body()?.let { produto: Product ->
                            _produtos.value += produto
                        }
                    } else {
                        errorList.add("Erro ao salvar item: ${resposta.message()}")
                    }

                } catch (e: Exception) {
                    errorList.add(e.message ?: "Erro desconhecido")
                    saveResult = Result.failure(e)

                } finally {
                    if (errorList.isNotEmpty()) {
                        _erros.value = errorList
                    }
                    atualizarLista()
                    _chamandoApi = false
                }
            }

        } else {
            Log.e("API", "Erros encontrados: ${errorList.joinToString(", ")}")
        }

    }




    private fun limparErros() {
        _erros.value = emptyList()
    }
}
