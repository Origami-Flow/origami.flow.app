package com.trancas.salgado.screens.stock

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.stock.classes.Product
import com.trancas.salgado.service.ClientService
import com.trancas.salgado.service.StockService
import com.trancas.salgado.ui.utils.criarRequestBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UpdateProductViewModel (private val api: StockService): ViewModel() {
    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    private val _updateResult = MutableStateFlow<Result<Unit>?>(null)
    val updateResult: StateFlow<Result<Unit>?> = _updateResult

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadProduct(productId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = api.getProductById(productId)
                val responseStock = api.getStockById(productId)
                Log.d("API", "Produto: ${response.body()}")
                if (response.isSuccessful && responseStock.isSuccessful) {
                    _product.value = response.body()
                    _product.value?.quantidade = responseStock.body()?.quantidade ?: 0
                } else {
                    Log.e("API", "Erro ao carregar produto: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Erro ao carregar produto: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateProduct(product: Product) {
        if (!isProductValid(product)) {
            _updateResult.value = Result.failure(Exception("Dados do produto inválidos"))
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = api.updateProduct(
                    id = product.id!!,
                    nome = criarRequestBody(product.nome),
                    marca = criarRequestBody(product.marca),
                    valorCompra = criarRequestBody(product.valorCompra.toString()),
                    valorVenda = criarRequestBody(product.valorVenda.toString()),
                    quantidadeEmbalagem = criarRequestBody(product.quantidadeEmbalagem.toString()),
                    unidadeMedida = criarRequestBody(product.unidadeMedida),
                    tipo = criarRequestBody(product.tipo),
                    imagem = product.imagem,
                    quantidade = criarRequestBody(product.quantidade.toString()),
                    salaoId = criarRequestBody(product.salaoId.toString())
                )
                val responseStock = api.updateStock(product.id, product.quantidade, true)
                if (response.isSuccessful && responseStock.isSuccessful) {
                    _updateResult.value = Result.success(Unit)
                    Log.d("API", "Produto atualizado com sucesso!")
                } else {
                    _updateResult.value = Result.failure(Exception("Erro ao atualizar produto: ${response?.message()}"))
                    Log.e("API", "Erro ao atualizar produto: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _updateResult.value = Result.failure(e)
                Log.e("API", "Erro ao atualizar produto: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun isProductValid(product: Product): Boolean {
        return product.nome.isNotBlank() &&
                product.marca.isNotBlank() &&
                product.valorCompra > 0 &&
                product.valorVenda >= 0 &&
                product.quantidadeEmbalagem > 0 &&
                product.unidadeMedida.isNotBlank() &&
                product.tipo.isNotBlank() &&
                product.quantidade > 0

    }
}