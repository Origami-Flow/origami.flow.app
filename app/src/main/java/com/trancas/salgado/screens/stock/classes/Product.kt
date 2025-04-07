package com.trancas.salgado.screens.stock.classes

data class Product(
    val id: Int,
    val nome: String,
    val marca: String,
    val valorCompra: Double,
    val valorVenda: Double,
    val quantidadeEmbalagem: Int,
    val unidadeMedida: String,
    val tipo: String
)
