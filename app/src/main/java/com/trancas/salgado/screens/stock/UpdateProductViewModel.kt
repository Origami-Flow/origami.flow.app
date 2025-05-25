package com.trancas.salgado.screens.stock

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.login.Login
import com.trancas.salgado.screens.stock.classes.Product
import com.trancas.salgado.service.StockService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UpdateProductViewModel : ViewModel() {
    val api = StockService.api
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
                if (response.isSuccessful) {
                    _product.value = response.body()
                    Log.d("API", "Produto carregado com sucesso!: ${response.body()}")
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
                val response = product.id?.let { api.updateProduct(it, product) }
                if (response != null && response.isSuccessful) {
                    _updateResult.value = Result.success(Unit)
                    Log.d("API", "Produto atualizado com sucesso!")
                } else {
                    _updateResult.value = Result.failure(Exception("Erro ao atualizar produto: ${response?.message()}"))
                    Log.e("API", "Erro ao atualizar produto: ${response?.errorBody()?.string()}")
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
                product.tipo.isNotBlank()
    }
}